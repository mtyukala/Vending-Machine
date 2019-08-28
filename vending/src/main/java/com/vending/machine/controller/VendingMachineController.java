package com.vending.machine.controller;

import com.vending.machine.model.Coin;
import com.vending.machine.model.Product;
import com.vending.machine.model.Purchase;
import com.vending.machine.repository.CoinRepository;
import com.vending.machine.repository.ProductRepository;
import com.vending.machine.repository.PurchaseRepository;
import com.vending.machine.utils.ResourceNotFoundException;
import com.vending.machine.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Class to define methods to perform operations on products
 *
 * @author Mkhululi Tyukala
 */
@CrossOrigin // --- not sure about security considerations
@RestController
public class VendingMachineController {
    @Value("${spring.application.name}")
    String appName;
    Logger logger = LoggerFactory.getLogger(VendingMachineController.class.getCanonicalName());
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CoinRepository coinRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView("home");
        model.addObject("appName", appName);
        model.addObject("products", productRepository.findAll());
        model.addObject("coins", coinRepository.findAll());
        return model;
    }


    @ApiOperation(value = "List all products in the system")
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @ApiOperation(value = "Gets the object matching the given id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @ApiOperation(value = "List all acceptable coins in the system")
    @GetMapping("/api/coins")
    public List<Coin> getCoins() {
        return coinRepository.findAll();
    }

    @ApiOperation(value = "Add a new product in the system")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/products")
    public Product createProducts(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @ApiOperation(value = "Add a new coin into the system")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/coins")
    public Coin createCoin(@Valid @RequestBody Coin coin) {
        return coinRepository.save(coin);
    }

    @ApiOperation(value = "Update a product")
    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productRepository.findById(id).map(p -> {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setWeight(product.getWeight());
            return ResponseEntity.ok(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Product with " + id + " not found."));
    }

    @ApiOperation(value = "Delete a Product")
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

    @ApiOperation(value = "Update Coin")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/coins/{id}")
    public ResponseEntity<Coin> updateCoin(@PathVariable Long id, @Valid @RequestBody Coin coin) {
        return coinRepository.findById(id).map(c -> {
            c.setAmount(coin.getAmount());
            c.setDescription(coin.getDescription());
            return ResponseEntity.ok(c);
        }).orElseThrow(() -> new ResourceNotFoundException("Coin matching " + id + ", not found"));

    }

    @ApiOperation(value = "Create a purchase")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/api/buy", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<Coin> buy(@Valid @RequestBody Purchase purchase) {
        logger.info(purchase.toString());
        logger.info(purchase.getProduct().toString());
        Long id = purchase.getProduct().getId();
        if (id == null) {
            throw new ResourceNotFoundException("Product cannot be determined for this purchase");
        }
        logger.info(id == null ? "product id is null" : id.toString());
        List<Coin> systemCoins = coinRepository.findAll();
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with [id] " + id + " not found"));
        // --- update products
        if (product.getItems() < 1) {
            throw new ResourceNotFoundException("Products out of stock");
        }

        double changeRendered = purchase.getAmount() - product.getPrice();
        if (changeRendered < 0) {
            throw new ResourceNotFoundException("Insufficient coins");
        }

        List<Coin> changeInCoins = Utils.toCoin(systemCoins, changeRendered);
        logger.info(changeInCoins.toString());
        if (!Utils.isAcceptable(changeInCoins, systemCoins)) {
            //TODO throw new ResourceNotFoundException("The inserted coins are not acceptable");
        }

//        double amount = 0;
        //for (Coin coin : changeInCoins) {
        //amount += coin.getAmount();
        //}

        // --- update products
        product.setItems(product.getItems() - 1);
        productRepository.save(product);

        // --- save purchase
        purchaseRepository.save(purchase);

        List<Coin> priceInCoins = Utils.toCoin(systemCoins, (double) product.getPrice());

        // --- update coins with the price of the selected product
        //      coinRepository.saveAll(changeInCoins);
//        List<Coin> change = Collections.emptyList();
        for (Coin coin : priceInCoins) {
            Coin systemCoin = coinRepository.getOne(coin.getCid());
            systemCoin.setCount(systemCoin.getCount() + coin.getCount());
            coinRepository.save(systemCoin);
        }/*
        if (amount > product.getPrice()) {
            change = makeChange(product.getPrice() - amount);
            logger.info("Change = " + change.toString());
            for (Coin coin : change) {
                coin.setCount(coin.getCount() - 1);
                coinRepository.save(coin);
            }
        }*/

        // --- return change TODO
        //Map<Product, List<Coin>> response = new HashMap<>();
        //response.put(product, change);
        return changeInCoins;
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
        return Utils.toCoin(coinRepository.findAll(), amount);
    }
}
