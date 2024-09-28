package org.bookstore.demo.entity;

public enum OrderState {
    SUBMITTED("submitted"),
    CANCELLED("cacelled");

    private String value;

    OrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
