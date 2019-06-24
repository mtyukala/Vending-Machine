package com.vending.machine.model;

import com.vending.machine.utils.Utils;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchase")
public class Purchase extends AuditModel implements Comparable<Purchase> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "purchase_generator")
    private Long pid;
    @Column(nullable = false)
    private Integer quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id")
    private Product product;

    private Double amount;

    public Purchase() {
        super();
        pid = 0L;
        product = new Product();
        amount = 0D;
        quantity = 0;
    }

    public Purchase(Double amount, Product product, Integer quantity) {
        setAmount(amount);
        setProduct(product);
        this.quantity = quantity;
    }

    public Purchase(List<Coin> coins, Product product, Integer quantity) {
        this(Utils.getAmount(coins),
                product, quantity);
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
        if (amount == null) {
            return false;
        } else if (!product.equals(other.getProduct())) {
            return false;
        } else if (!amount.equals(other.getAmount())) {
            return false;
        } else if (quantity != other.getQuantity()) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Purchase purchase) {
        int BEFORE = -1;
        int EQUAL = 0;
        int AFTER = 1;

        if (this == purchase) {
            return EQUAL;
        }

        if (amount < purchase.getAmount()) {
            return BEFORE;
        }
        if (amount > purchase.getAmount()) {
            return AFTER;
        }

        if (quantity < purchase.getQuantity()) {
            return BEFORE;
        }
        if (quantity > purchase.getQuantity()) {
            return AFTER;
        }

        return EQUAL;

    }

    @Override
    public String toString() {
        String result = "[Purchase Details, " + product.toString();
        result += ", Amount = " + amount;
        result += ", Quantity = " + quantity;
        return result + "]";
    }
}
