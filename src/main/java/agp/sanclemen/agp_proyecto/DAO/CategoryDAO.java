package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.model.Category;

import java.sql.*;
import java.util.List;

public class CategoryDAO implements DAO<Category> {

    private final Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Category get(long id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

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
