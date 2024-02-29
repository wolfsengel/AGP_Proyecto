package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.model.CartItem;
import jakarta.persistence.EntityManager;

import java.sql.*;
import java.util.List;

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