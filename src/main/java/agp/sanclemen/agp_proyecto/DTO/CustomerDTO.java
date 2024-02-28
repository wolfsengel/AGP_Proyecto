package agp.sanclemen.agp_proyecto.DTO;

import java.util.Date;

public class CustomerDTO {
    private long id;
    private String name;
    private String password;
    private Date lastUpdated;
    private Date registrationDate;

    public CustomerDTO() {
    }

    public CustomerDTO(long id, String name, String password, Date lastUpdated, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lastUpdated = lastUpdated;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
