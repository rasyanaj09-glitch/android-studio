package com.example.myapplication;

import java.io.Serializable;

public class roduct implements Serializable {
    public int Id;
    public String Name;
    public String Price;
    public int Stock;

    // Constructor Baru (Hanya 4 Parameter)
    public roduct(int id, String name, String price, int stock) {
        this.Id = id;
        this.Name = name;
        this.Price = price;
        this.Stock = stock;
    }

    // Getter yang disesuaikan
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public int getStock() {
        return Stock;
    }
}
