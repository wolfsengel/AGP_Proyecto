package agp.sanclemen.agp_proyecto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CART_ITEM")
public class CartItem {
    @EmbeddedId
    private CartItemKey id;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "ITEM_QTY", nullable = false)
    private int itemQty;

    @Column(name = "LAST_UPDATED", nullable = false)
    private Date lastUpdated;

}
