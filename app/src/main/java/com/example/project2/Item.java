package com.example.project2;

public class Item {

    private String type;
    private String category;
    private String title;
    private String description;
    private String date;
    private String phone;
    private String imageUri;
    private boolean resolved;

    public Item(String type,
                String category,
                String title,
                String description,
                String date,
                String imageUri) {

        this.type = type;
        this.category = category;
        this.title = title;
        this.description = description;
        this.date = date;

        // Demo phone number
        this.phone = "9876543210";

        this.imageUri = imageUri;
        this.resolved = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageUri() {
        return imageUri;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}