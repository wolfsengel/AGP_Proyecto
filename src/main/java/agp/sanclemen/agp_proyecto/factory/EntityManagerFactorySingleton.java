package agp.sanclemen.agp_proyecto.factory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {

    private static volatile EntityManagerFactory instance;
    private static final String PERSISTENCE_UNIT_NAME = "AGP_PROYECTO";

    // Private constructor to prevent instantiation from outside
    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (instance == null) {
            synchronized (EntityManagerFactorySingleton.class) {
                if (instance == null) {
                    instance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                }
            }
        }
        return instance;
    }

    public static void closeEntityManagerFactory() {
        if (instance != null && instance.isOpen()) {
            instance.close();
        }
    }
}
