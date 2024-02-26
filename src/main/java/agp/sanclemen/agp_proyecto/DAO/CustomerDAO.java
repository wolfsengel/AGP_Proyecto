package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.model.Customer;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.sql.*;

/*
*CREATE TABLE IF NOT EXISTS CUSTOMER (
    ID INT PRIMARY KEY NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(20) NOT NULL,
    LAST_UPDATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    REGISTRATION_DATE DATE DEFAULT CURRENT_DATE NOT NULL
); */

public class CustomerDAO implements DAO<Customer> {

    private final EntityManager entityManager;

    public CustomerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Customer get(long id) {
        try {
            return entityManager.find(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public List<Integer> getAllIds() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
