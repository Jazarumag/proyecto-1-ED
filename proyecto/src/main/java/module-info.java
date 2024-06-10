module com.espol.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens com.espol.control to javafx.fxml;
    exports com.espol.proyecto;
}
