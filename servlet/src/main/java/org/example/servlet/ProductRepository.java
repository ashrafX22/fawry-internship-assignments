package org.example.servlet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductRepository {
    public static List<Product> getProducts() {
        return IntStream.range(1, 501) // Generates numbers from 1 to 500
                .mapToObj(i -> ProductFactory.createProduct(
                        (long) i,
                        "Product " + i,
                        "Description for product " + i,
                        Math.round((Math.random() * 1000 + 10) * 100.0) / 100.0, // Price between 10 and 1000
                        (int) (Math.random() * 100 + 1) // Quantity between 1 and 100
                ))
                .collect(Collectors.toList());
    }
}