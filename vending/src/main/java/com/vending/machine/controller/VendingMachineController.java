package com.vending.machine.controller;

import com.vending.machine.model.Coin;
import com.vending.machine.model.Product;
import com.vending.machine.model.Purchase;
import com.vending.machine.repository.CoinRepository;
import com.vending.machine.repository.ProductRepository;
import com.vending.machine.repository.PurchaseRepository;
import com.vending.machine.utils.ResourceNotFoundException;
import com.vending.machine.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

//import io.swagger.annotations.ApiOperation;

/**
 * Class to define methods to perform operations on products
 *
 * @author Mkhululi Tyukala
 */
@RestController
@Transactional
public class VendingMachineController {
    @Value("${spring.application.name}")
    String appName;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CoinRepository coinRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }


    //@ApiOperation(value = "List all products in the system")
    @GetMapping("/api/products")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    //@ApiOperation(value = "List all acceptable coins in the system")
    @GetMapping("/api/coins")
    public Page<Coin> getCoins(Pageable pageable) {
        return coinRepository.findAll(pageable);
    }

    //@ApiOperation(value = "Add a new product in the system")
    @PostMapping("/api/products")
    public Product createProducts(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    //@ApiOperation(value = "Add a new coin into the system")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/coins")
    public Coin createCoin(@Valid @RequestBody Coin coin) {
        return coinRepository.save(coin);
    }

    //@ApiOperation(value = "Update a product")
    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productRepository.findById(id).map(p -> {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setWeight(product.getWeight());
            return ResponseEntity.ok(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Product with " + id + " not found."));
    }

    ////@ApiOperation(value = "Delete a Product")
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with " + id + " not found.");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with " + id + " not found."));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/coins/{id}")
    public ResponseEntity<Coin> updateCoin(@PathVariable Long id, @Valid @RequestBody Coin coin) {
        return coinRepository.findById(id).map(c -> {
            c.setAmount(coin.getAmount());
            c.setDescription(coin.getDescription());
            return ResponseEntity.ok(c);
        }).orElseThrow(() -> new ResourceNotFoundException("Coin matching " + id + ", not found"));

    }

    ////@ApiOperation(value = "Create a new purchase into the system")
    @PostMapping("/api/buy")
    public Map<Product, List<Coin>> buy(@Valid @RequestBody Product product, @Valid @RequestBody List<Coin> coins) {

        Product productToBuy = productRepository.findById(product.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with name" + product.getName() + " not found"));

        if (!Utils.isAcceptable(coins, coins)) {
            throw new ResourceNotFoundException("The inserted coins are not acceptable");
        }

        // --- update products
        if (productToBuy.getItems() < 1) {
            throw new ResourceNotFoundException("Products put of stock");
        }

        double amount = 0;
        for (Coin coin : coins) {
            amount += coin.getAmount();
        }

        if (amount < product.getPrice()) {
            throw new ResourceNotFoundException("Insufficient coins");
        }

        // --- update products
        product.setItems(product.getItems() - 1);
        productRepository.save(product);

        // --- save purchase
        Purchase purchase = new Purchase(coins, product, 1);
        purchaseRepository.save(purchase);

        // --- update coins work out change
        coinRepository.saveAll(coins);
        List<Coin> change = Collections.emptyList();
        if (amount > product.getPrice()) {
            change = makeChange(product.getPrice() - amount);
            for (Coin coin : change) {
                coin.setCount(coin.getCount() - 1);
                coinRepository.save(coin);
            }
        }

        // --- return change TODO
        Map<Product, List<Coin>> response = new HashMap<>();
        response.put(product, change);
        return response;
    }

    @GetMapping("/api/cancel")
    public String cancel() {
        return "redirect:/api/products";
    }

    /**
     * Converts the given amount into a list of coins
     *
     * @param amount the amount of change in cents
     * @return list of coins as a change for the purchase
     */
    public List<Coin> makeChange(double amount) {
        if (amount <= 0) {
            return Collections.emptyList();
        }

        List<Coin> list = Collections.emptyList();
        List<Coin> coins = coinRepository.findAll(); // coins are unique in the system
        Collections.sort(coins); // sort from Large to Small

        for (Coin coin : coins) {
            if (amount > coin.getAmount()) {
                int money = (int) (amount / coin.getAmount());
                IntStream.range(0, money).mapToObj(i -> coin).forEachOrdered(list::add);

                amount -= money;
            }
        }
        return list;
    }
}
