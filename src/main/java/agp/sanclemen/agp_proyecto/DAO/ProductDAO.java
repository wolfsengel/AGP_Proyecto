package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.model.Product;

import java.util.List;
import java.sql.*;

public class ProductDAO implements DAO<Product> {

    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product get(long id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

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
