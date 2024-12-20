package org.example.servlet;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ProductCRUD", value = "/products")
public class HelloServlet extends HttpServlet {
    protected final List<Product> products = ProductRepository.getProducts();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            res.setContentType("application/json");
            Map<String, String> params = new HashMap<>();
            req.getParameterMap().forEach((key, value) -> params.put(key, value[0]));
            List<Product>filteredProducts = products;
            if(params.containsKey("id")){
                filteredProducts = filteredProducts.stream().filter(product -> product.getId() == Long.parseLong(params.get("id"))).collect(Collectors.toList());
            }
            if(params.containsKey("name")){
                filteredProducts = filteredProducts.stream().filter(product -> product.getName().toLowerCase().contains(params.get("name").toLowerCase())).collect(Collectors.toList());
            }
            if(params.containsKey("price")){
                filteredProducts = filteredProducts.stream().filter(product -> product.getPrice() == Double.parseDouble(params.get("price"))).collect(Collectors.toList());
            }
            if(params.containsKey("quantity")){
                filteredProducts = filteredProducts.stream().filter(product -> product.getQuantity() == Integer.parseInt(params.get("quantity"))).collect(Collectors.toList());
            }
            res.getWriter().write(gson.toJson(filteredProducts));

            res.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
            Product newProduct = gson.fromJson(req.getReader(), Product.class);
            newProduct.setId((long) (products.size()+1));
            products.add(newProduct);

            res.setStatus(HttpServletResponse.SC_CREATED);
            res.getWriter().write(gson.toJson(newProduct));
        res.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("id"));
        Optional<Product> product  = products.stream().filter(product1 -> product1.getId() == productId ).findFirst();
        if (product.isPresent()) {
            res.setContentType("application/json");
          Product updatedProduct = gson.fromJson(req.getReader(), Product.class);
            updatedProduct.setId(productId);
            product.get().setName(updatedProduct.getName());
            product.get().setDescription(updatedProduct.getDescription());
            product.get().setPrice(updatedProduct.getPrice());
            product.get().setQuantity(updatedProduct.getQuantity());
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write(gson.toJson(product.get()));
            res.getWriter().flush();
        }
        else {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("id"));
        Optional<Product> product  = products.stream().filter(product1 -> product1.getId() == productId ).findFirst();
        if (product.isPresent()) {
            products.remove(product.get());
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}