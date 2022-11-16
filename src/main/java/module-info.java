module com.example.tarea2loslavadores {
    requires javafx.controls;
    requires javafx.fxml;

    opens Vista to javafx.fxml;
    exports Vista;
}