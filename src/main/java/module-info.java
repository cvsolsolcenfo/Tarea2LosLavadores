module com.example.tarea2loslavadores {
    requires javafx.controls;
    requires javafx.fxml;


    opens Controlador to javafx.fxml;
    exports Controlador;
    exports Vista;
    opens Vista to javafx.fxml;
}