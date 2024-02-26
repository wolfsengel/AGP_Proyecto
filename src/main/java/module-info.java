module agp.sanclemen.agp_proyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens agp.sanclemen.agp_proyecto to javafx.fxml;
    exports agp.sanclemen.agp_proyecto;
}