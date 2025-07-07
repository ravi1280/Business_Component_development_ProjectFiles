package lk.jiat.ee.core.service;

import jakarta.ejb.Remote;
import lk.jiat.ee.core.model.Product;

import java.util.List;
import java.util.Optional;

@Remote
public interface ProductService {
    Optional<Product> getProductById(long id);
    Optional<Product> getProductByName(String name);
    List<Product> getProductByCategory(String category);
    List<Product> getAllProduct();

    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(long id);



}
