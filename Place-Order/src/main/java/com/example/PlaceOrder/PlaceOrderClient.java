package com.example.PlaceOrder;

import com.example.PlaceOrder.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Feign Client used to consume the productdetails microservice
 */
@FeignClient("productdetails")
public interface PlaceOrderClient {

    /**
     * @return List of all the available products
     */
    @GetMapping("/products")
    public List<Product> getAllProducts();

    /**
     * @param itemName
     * @return Product details of a specific item name.
     */
    @GetMapping(value="/products/itemName/{itemName}",produces="application/json")
    public Product getProductByName(String itemName);
}
