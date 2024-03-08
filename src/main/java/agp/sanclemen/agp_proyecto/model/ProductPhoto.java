package agp.sanclemen.agp_proyecto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_PHOTO")
public class ProductPhoto {

    @Id
    @Column(name = "ID")
    private Long productId;

    @Lob
    @Column(name = "PHOTO")
    private byte[] photoData;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private Product product;
}
