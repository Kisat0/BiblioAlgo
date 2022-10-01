module com.example.biblioalgo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.biblioalgo to javafx.fxml;
    exports com.example.biblioalgo;
}