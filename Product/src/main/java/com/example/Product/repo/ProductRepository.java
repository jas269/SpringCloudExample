package com.example.Product.repo;

import com.example.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository class for Product Application
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findProductByItemName(String itemName);
}
