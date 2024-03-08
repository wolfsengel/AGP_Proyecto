package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.factory.EntityManagerFactorySingleton;
import agp.sanclemen.agp_proyecto.model.ProductPhoto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductPhotoDAO implements DAO<ProductPhoto>{
    private final EntityManager entityManager;

    public ProductPhotoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public ProductPhoto get(long id) {
        try {
            return entityManager.find(ProductPhoto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductPhoto> getAll() {
        try {
            return entityManager.createQuery("from ProductPhoto").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(ProductPhoto productPhoto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(productPhoto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductPhoto productPhoto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(productPhoto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ProductPhoto productPhoto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(productPhoto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(long id) {
        try {
            ProductPhoto productPhoto = get(id);
            if (productPhoto != null) {
                delete(productPhoto);
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
            return entityManager.createQuery("select id from ProductPhoto").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from ProductPhoto").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
