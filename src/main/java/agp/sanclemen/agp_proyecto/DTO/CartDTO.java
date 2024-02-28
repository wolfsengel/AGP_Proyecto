package agp.sanclemen.agp_proyecto.DTO;

public class CartDTO {
    private Long id;
    private Long customerId;
    private String name;

    public CartDTO() {
    }

    public CartDTO(Long id, Long customerId, String name) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
