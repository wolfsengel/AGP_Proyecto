package agp.sanclemen.agp_proyecto.model;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "LAST_UPDATED", nullable = false)
    private Date lastUpdated;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private Date registrationDate;

    public Customer() {
    }

    public Customer(String name, String password, Date lastUpdated, Date registrationDate) {
        this.name = name;
        this.password = password;
        this.lastUpdated = lastUpdated;
        this.registrationDate = registrationDate;
    }

    public Customer(long id, String name, String password, Date lastUpdated, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lastUpdated = lastUpdated;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", registrationDate=" + registrationDate +
                '}';
    }
}

