package org.example.servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ProductCRUD", value = "/products")
public class HelloServlet extends HttpServlet {
    private final List<Product> products = ProductRepository.getProducts();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            res.setContentType("application/json");
    }
}