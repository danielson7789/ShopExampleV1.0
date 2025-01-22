package org.example.shop.enums;

public enum Sorting {
    PRICE_ASC ("Price low -> high"),
    PRICE_DESC ("Price high -> low"),
    RATING_ASC ("Rating low -> high"),
    RATING_DESC ("Rating high -> low"),
    NAME_ASC ("Name (A-Z)"),
    NAME_DESC ("Name (Z-A)");

    public String label;
    private String selected;

    public String getLabel() {
        return label;
    }
    public String getSelected() {
        return selected;
    }
    Sorting(String label) {
        this.label = label;
    }
    public void setSelected(String selected) {
        this.selected = selected;
    }
}

