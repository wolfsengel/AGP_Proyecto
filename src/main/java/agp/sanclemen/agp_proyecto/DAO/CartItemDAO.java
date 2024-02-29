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
        return null;
    }

    @Override
    public List<CartItem> getAll() {
        return null;
    }

    @Override
    public void save(CartItem cartItem) {

    }

    @Override
    public void update(CartItem cartItem) {

    }

    @Override
    public void delete(CartItem cartItem) {

    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public List<Long> getAllIds() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}