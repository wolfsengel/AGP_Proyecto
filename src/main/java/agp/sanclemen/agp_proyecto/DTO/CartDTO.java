package agp.sanclemen.agp_proyecto.DTO;

public class CartDTO {
    private long id;
    private long customerId;
    private String name;

    public CartDTO() {
    }

    public CartDTO(int id, int customerId, String name) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
