package com.vending.machine.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vending.machine.model.Coin;
import com.vending.machine.model.Product;
import com.vending.machine.repository.CoinRepository;
import com.vending.machine.repository.ProductRepository;
import com.vending.machine.repository.PurchaseRepository;
import com.vending.machine.utils.ResourceNotFoundException;

/**
 * Class to define methods to perform operations on products
 * 
 * @author Mkhululi Tyukala
 *
 */
@RestController
@RequestMapping("/")
public class VendingMachineController {
	@Autowired
	private ProductRepository productRepository;
	private CoinRepository coinRepository;

	private PurchaseRepository purchaseRepository;

	@GetMapping("/api/products")
	public Page<Product> getProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@GetMapping("/api/coins")
	public Page<Coin> getCoins(Pageable pageable) {
		return coinRepository.findAll(pageable);
	}

	@PostMapping("/api/products")
	public Product createProducts(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}

	@PostMapping("/api/coins")
	public Coin createCoin(@Valid @RequestBody Coin coin) {
		return coinRepository.save(coin);
	}

	@PutMapping("/api/products/{id}")
	public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
		return productRepository.findById(id).map(p -> {
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			p.setWeight(product.getWeight());
			return productRepository.save(p);
		}).orElseThrow(() -> new ResourceNotFoundException("Product with " + id + " not found."));
	}

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
	public Coin updateCoin(@PathVariable Long id, @Valid @RequestBody Coin coin) {
		return coinRepository.findById(id).map(c -> {
			c.setAmount(coin.getAmount());
			c.setDescription(coin.getDescription());
			return c;
		}).orElseThrow(() -> new ResourceNotFoundException("Coin matching " + id + ", not found"));

	}

	@PostMapping("/api/buy")
	public Map<Product, List<Coin>> buy(@Valid @RequestBody Product product) {
		return null;
	}

}
