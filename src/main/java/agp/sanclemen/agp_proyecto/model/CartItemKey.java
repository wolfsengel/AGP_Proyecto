package agp.sanclemen.agp_proyecto.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartItemKey implements Serializable {

    private long customerId;

    private long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemKey)) return false;
        CartItemKey that = (CartItemKey) o;
        return customerId == that.customerId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
    }
}
