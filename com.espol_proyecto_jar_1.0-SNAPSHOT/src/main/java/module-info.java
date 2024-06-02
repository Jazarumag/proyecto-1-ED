module com.espol.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.espol.proyecto to javafx.fxml;
    exports com.espol.proyecto;
}
