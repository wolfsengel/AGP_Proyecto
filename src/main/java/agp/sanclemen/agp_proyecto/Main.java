package agp.sanclemen.agp_proyecto;

import agp.sanclemen.agp_proyecto.factory.EntityManagerFactorySingleton;

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

            // Crear un nuevo Customer
            Customer customer = new Customer();
            customer.setName("Juan");
            customer.setPassword("1234");
            customer.setRegistrationDate(new Date(System.currentTimeMillis()));
            customer.setLastUpdated(new Date(System.currentTimeMillis()));

            // Iniciar una transacción
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            System.out.println("Customer ID: " + customer.getId() + " Name: " + customer.getName()+" ... Finalizado");

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

