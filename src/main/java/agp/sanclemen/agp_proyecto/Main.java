package agp.sanclemen.agp_proyecto;

import agp.sanclemen.agp_proyecto.DAO.CartItemDAO;
import agp.sanclemen.agp_proyecto.DAO.CustomerDAO;
import agp.sanclemen.agp_proyecto.DAO.DAO;
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
            DAO<Customer> customerDAO = new CustomerDAO(entityManager);
            // MOSTRAR TODOS LOS CUSTOMERS
            System.out.println("MOSTRAR TODOS LOS CUSTOMERS");
            customerDAO.getAll().forEach(System.out::println);
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

