package agp.sanclemen.agp_proyecto.factory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {

    private static volatile EntityManagerFactory instance;

    // Private constructor to prevent instantiation from outside
    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (instance == null) {
            synchronized (EntityManagerFactorySingleton.class) {
                if (instance == null) {
                    instance = Persistence.createEntityManagerFactory("AGP_PROYECTO");
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
