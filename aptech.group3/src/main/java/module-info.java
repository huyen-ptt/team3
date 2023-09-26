module khutro.aptech.group3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires java.base;

    opens khutro.aptech.group3 to javafx.fxml;
    opens khutro.aptech.group3.controller to javafx.fxml;
    opens khutro.aptech.group3.model to javafx.base; // Open the model package to JavaFX

    exports khutro.aptech.group3;
    exports khutro.aptech.group3.controller;
    exports khutro.aptech.group3.model;
    exports khutro.aptech.group3.dao;
    exports khutro.aptech.group3.database;
}
