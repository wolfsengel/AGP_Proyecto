package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.model.Product;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.sql.*;

/*
CREATE TABLE IF NOT EXISTS PRODUCT (
    ID INT PRIMARY KEY NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION CLOB NOT NULL,
    PRICE DECIMAL(5,2) NOT NULL,
    STOCK_QTY INT NOT NULL,
    LAST_UPDATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CATEGORY_ID INT NOT NULL
);
 */
public class ProductDAO implements DAO<Product> {

    private final EntityManager entityManager;

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product get(long id) {
        try {
            return entityManager.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        try {
            return entityManager.createQuery("from Product").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(long id) {
        try {
            Product product = get(id);
            delete(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getAllIds() {
        try {
            return entityManager.createQuery("select id from Product").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try {
            entityManager.createQuery("delete from Product").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
