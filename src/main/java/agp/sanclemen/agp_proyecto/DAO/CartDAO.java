package agp.sanclemen.agp_proyecto.DAO;
import agp.sanclemen.agp_proyecto.model.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/*
CREATE TABLE IF NOT EXISTS CART (
    ID INT PRIMARY KEY NOT NULL,
    CUSTOMER_ID INT NOT NULL,
    NAME VARCHAR(50) NOT NULL
);
 */
public class CartDAO implements DAO<Cart> {

    private final EntityManager entityManager;

    public CartDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cart get(long id) {
        try {
            return entityManager.find(Cart.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cart> getAll() {
        try {
            return entityManager.createQuery("from Cart").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Cart cart) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(cart);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cart cart) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(cart);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cart cart) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(cart);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(long id) {
        try {
            Cart cart = get(id);
            delete(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getAllIds() {
        try {
            return entityManager.createQuery("select id from Cart").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.createQuery("delete from Cart").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cart> getCartsInCartItem() {
        try {
            return entityManager.createQuery("select distinct cart from CartItem").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
