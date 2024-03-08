package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.model.CartItem;
import jakarta.persistence.EntityManager;

import java.sql.*;
import java.util.List;

/*
CREATE TABLE IF NOT EXISTS CART_ITEM (
    ID INT PRIMARY KEY NOT NULL,
    CART_ID INT NOT NULL,
    PRODUCT_ID INT NOT NULL,
    ITEM_QTY INT NOT NULL,
    LAST_UPDATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
    );
 */
public class CartItemDAO implements DAO<CartItem> {

    private final EntityManager entityManager;

    public CartItemDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CartItem get(long id) {
        try {
            return entityManager.find(CartItem.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CartItem> getAll() {
        try {
            entityManager.getTransaction().begin();
            List<CartItem> cartItems = entityManager.createQuery("from CartItem").getResultList();
            entityManager.getTransaction().commit();
            return cartItems;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(CartItem cartItem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cartItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CartItem cartItem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cartItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CartItem cartItem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(cartItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(long id) {
        try {
            CartItem cartItem = get(id);
            if (cartItem != null) {
                delete(cartItem);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getAllIds() {
        try {
            entityManager.getTransaction().begin();
            List<Long> ids = entityManager.createQuery("select id from CartItem").getResultList();
            entityManager.getTransaction().commit();
            return ids;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from CartItem").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}