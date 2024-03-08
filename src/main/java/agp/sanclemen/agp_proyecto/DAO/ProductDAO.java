package agp.sanclemen.agp_proyecto.DAO;

import agp.sanclemen.agp_proyecto.DTO.ProductDTO;
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
public class ProductDAO implements DAO_DTO<Product, ProductDTO> {

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
    public List<Product> getAll(){
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

    @Override
    public List<ProductDTO> getAllDTO() {
        try {
            // Query para todos los productos DTO
            return entityManager.createQuery(
                    """
                            select new agp.sanclemen.agp_proyecto.DTO.ProductDTO(p.id, p.name,
                            p.description, p.price, p.stockQty,p.lastUpdated, c.name, c.description)
                            from Product p
                            join Category c on p.category.id = c.id
                            group by p.id
                        """, ProductDTO.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDTO> getProductsByClient(long id){
        try {
            // Query para todos los productos por cliente
            return entityManager.createQuery(
                    """
                            select new agp.sanclemen.agp_proyecto.DTO.ProductDTO(p.id, p.name,
                            p.description, p.price, p.stockQty,p.lastUpdated, c.name, c.description)
                            from Product p
                            join Category c on p.category.id = c.id
                            join CartItem ci on p.id = ci.product.id
                            join Customer cu on ci.cart.id = cu.id
                            where cu.id = :id
                            group by p.id
                        """, ProductDTO.class).setParameter("id", id).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
