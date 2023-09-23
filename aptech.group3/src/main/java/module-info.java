module khutro.aptech.group3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;

    opens khutro.aptech.group3 to javafx.fxml;
    opens khutro.aptech.group3.controller to javafx.fxml;

    exports khutro.aptech.group3;
    exports khutro.aptech.group3.controller;
}
