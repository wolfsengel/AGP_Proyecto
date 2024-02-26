package agp.sanclemen.agp_proyecto.DAO;
import agp.sanclemen.agp_proyecto.model.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Connection;
import java.util.List;

public class CartDAO implements DAO<Cart> {

    private final Connection connection;

    public CartDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Cart get(long id) {
        return null;
    }

    @Override
    public List<Cart> getAll() {
        return null;
    }

    @Override
    public void save(Cart cart) {

    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public void delete(Cart cart) {

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
