module agp.sanclemen.agp_proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires lombok;

    opens agp.sanclemen.agp_proyecto to javafx.fxml;
    opens agp.sanclemen.agp_proyecto.model to org.hibernate.orm.core;
    opens agp.sanclemen.agp_proyecto.DTO to org.hibernate.orm.core;
    opens agp.sanclemen.agp_proyecto.DAO to org.hibernate.orm.core;
    exports agp.sanclemen.agp_proyecto;
    exports agp.sanclemen.agp_proyecto.view;
    exports agp.sanclemen.agp_proyecto.model;
    exports agp.sanclemen.agp_proyecto.DTO;
    exports agp.sanclemen.agp_proyecto.DAO;
    opens agp.sanclemen.agp_proyecto.view to javafx.fxml;

}
