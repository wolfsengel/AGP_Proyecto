package agp.sanclemen.agp_proyecto.DTO;

import java.util.Date;

public class CartItemDTO {

    private long id;
    private long cartId;
    private long productId;
    private int itemQty;
    private Date lastUpdated;

    public CartItemDTO() {
    }

    public CartItemDTO(long id, long cartId, long productId, int itemQty, Date lastUpdated) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.itemQty = itemQty;
        this.lastUpdated = lastUpdated;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
}
