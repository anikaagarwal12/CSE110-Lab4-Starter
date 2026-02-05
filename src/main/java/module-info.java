module edu.ucsd.spendingtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens edu.ucsd.spendingtracker to javafx.fxml;
    exports edu.ucsd.spendingtracker;
}
