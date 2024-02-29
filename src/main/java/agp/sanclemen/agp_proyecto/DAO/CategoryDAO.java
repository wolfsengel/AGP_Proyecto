package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;

import java.sql.*;
import java.util.List;

/*
CREATE TABLE IF NOT EXISTS CATEGORY (
    ID INT PRIMARY KEY NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION CLOB
);
 */
public class CategoryDAO implements DAO<Category> {

    private final EntityManager entityManager;

    public CategoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Category get(long id) {
        try {
            return entityManager.find(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        try {
            return entityManager.createQuery("from Category").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(long id) {
        try {
            Category category = get(id);
            if (category != null) {
                delete(category);
                return true;
            }
        } catch (TransactionRequiredException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getAllIds() {
        try {
            return entityManager.createQuery("select id from Category").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from Category").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
