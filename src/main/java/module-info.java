module com.namerek.core {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires spring.context;
    requires spring.beans;
    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires modelmapper;
    requires spring.data.commons;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.tx;
    requires spring.core;
    requires spring.data.jpa;
    requires net.rgielen.fxweaver.core;
    requires org.mockito;
    requires org.testng;
    requires java.persistence;

    exports com.namerek.core;
    exports com.namerek.core.Tests;
    exports com.namerek.core.PresentationLayer.Controllers;
    exports com.namerek.core.Models;
    exports com.namerek.core.Helpers.Utils;

    opens com.namerek.core.PresentationLayer.Controllers to javafx.fxml;
    opens com.namerek.core.Entities;
    opens com.namerek.core.DTOs;
    opens com.namerek.core.Models;
    opens com.namerek.core.Helpers.Utils to javafx.fxml;
    opens com.namerek.core to javafx.fxml;
    opens com.namerek.core.Tests;

}