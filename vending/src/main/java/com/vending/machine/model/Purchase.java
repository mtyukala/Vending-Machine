package com.vending.machine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    private Long id;
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "coin_id", nullable = false)
    private List<Coin> coins;

    public Purchase() {
        super();
    }

    public Purchase(List<Coin> coins, Product product, Integer quantity) {
        // pk = new PurchaseCoinPK();
        setCoins(coins);
        setProduct(product);
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Purchase other = (Purchase) obj;
        if (coins == null) {
            return false;
        } else if (!product.equals(other.getProduct())) {
            return false;
        } else if (!coins.equals(other.getCoins())) {
            return false;
        } else if (quantity != other.getQuantity()) {
            return false;
        }
        return true;
    }

}
