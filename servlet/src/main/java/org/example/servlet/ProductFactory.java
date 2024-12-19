package org.example.servlet;

public class ProductFactory {
    public static Product createProduct(Long id, String name, String description, double price, int quantity) {
        return new Product(id, name, description, price, quantity);
    }
}
