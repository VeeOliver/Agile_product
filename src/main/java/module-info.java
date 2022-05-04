module Myrmidon {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires transitive javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires transitive java.sql;
    requires org.knowm.xchart;
    requires javafx.swing;
    exports se.hkr.app;
    opens se.hkr.app to javafx.fxml;

}