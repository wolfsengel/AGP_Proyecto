package agp.sanclemen.agp_proyecto;

import agp.sanclemen.agp_proyecto.DAO.CartDAO;
import agp.sanclemen.agp_proyecto.DAO.CartItemDAO;
import agp.sanclemen.agp_proyecto.DAO.CustomerDAO;
import agp.sanclemen.agp_proyecto.DAO.ProductDAO;
import agp.sanclemen.agp_proyecto.factory.EntityManagerFactorySingleton;

import agp.sanclemen.agp_proyecto.model.CartItem;
import agp.sanclemen.agp_proyecto.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {
        // Obtener la instancia del EntityManagerFactory
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getEntityManagerFactory();

        // Crear un EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CartDAO cartDAO = new CartDAO(entityManager);
            CartItemDAO cartItemDAO = new CartItemDAO(entityManager);
            ProductDAO productDAO = new ProductDAO(entityManager);
            CartItem testcartitem = new CartItem();
            testcartitem.setLastUpdated(new Date(System.currentTimeMillis()));
            testcartitem.setItemQty(1);
            testcartitem.setCart(cartDAO.get(1));
            testcartitem.setProduct(productDAO.get(1));
            cartItemDAO.save(testcartitem);
            System.out.println("CartItem saved: " + testcartitem);

        } finally {
            // Cerrar el EntityManager
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }

            // Cerrar el EntityManagerFactory (esto generalmente se hace al apagar la aplicación)
            EntityManagerFactorySingleton.closeEntityManagerFactory();
        }
    }
}

