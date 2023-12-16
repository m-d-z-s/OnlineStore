module com.example.onlinestore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires annotations;

    opens me.mdzs.onlinestore to javafx.fxml;
    exports me.mdzs.onlinestore;

    opens me.mdzs.onlinestore.utils to javafx.fxml;
    exports me.mdzs.onlinestore.utils;

    opens me.mdzs.onlinestore.domain to javafx.fxml;
    exports me.mdzs.onlinestore.domain;

}