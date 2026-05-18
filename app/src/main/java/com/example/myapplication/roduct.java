package com.example.myapplication;

public class roduct {
    private String id;
    private String categoryId;
    private String name;
    private String description;
    private String price;
    private String stock;
    private String imageUrl;

    public roduct(String id, String categoryId, String name, String description, String price, String stock, String imageUrl) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public String getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public String getStock() { return stock; }
    public String getImageUrl() { return imageUrl; }
}
