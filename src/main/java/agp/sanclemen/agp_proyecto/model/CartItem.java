package agp.sanclemen.agp_proyecto.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CART_ITEM")
@IdClass(CartItem.CartItemKey.class)
public class CartItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "CART_ID", nullable = false)
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "ITEM_QTY", nullable = false)
    private int itemQty;

    @Column(name = "LAST_UPDATED", nullable = false)
    private Date lastUpdated;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static class CartItemKey implements Serializable {
        private int cart;
        private int product;

        public CartItemKey() {
        }

        public CartItemKey(int cart, int product) {
            this.cart = cart;
            this.product = product;
        }

        public int getCart() {
            return cart;
        }

        public void setCart(int cart) {
            this.cart = cart;
        }

        public int getProduct() {
            return product;
        }

        public void setProduct(int product) {
            this.product = product;
        }
    }
}
