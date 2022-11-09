package com.namerek.core.PresentationLayer;

public enum View {
    LOGIN("login.fxml"),
    MAIN("orders.fxml"),
    CREATE_ORDER("createOrderDialog.fxml"),
    SHOW_ORDER("showOrderDetails.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}