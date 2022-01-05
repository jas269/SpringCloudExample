package com.example.Product;

import com.example.Product.model.Product;
import com.example.Product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Controller Class of Product Application
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    /**
     * Api endpoint to get the List of all products available
     */
    @GetMapping
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    /**
     * Api endpoint to add a new Product
     */
    @PostMapping(value = "/addProduct")
    public Product addProduct(@RequestBody Product product) {
        productRepo.save(product);
        return product;
    }

    /**
     * Api endpoint to get the details of the product
     * @param itemName
     */
    @GetMapping(value = "/itemName/{itemName}")
    public Product getProductByItemName(@PathVariable("itemName") String itemName) {
        return productRepo.findProductByItemName(itemName);
    }

    /**
     * Api endpoint to get the details of the product
     * @param itemId
     */
    @GetMapping(value = "/itemId/{itemId}")
    public Product getProductByItemId(@PathVariable("itemId") Integer itemId) {
        return productRepo.getById(itemId);
    }

    /**
     * Api endpoint to update a product
     */
    @PutMapping(value = "/update")
    public Product updateProduct(@RequestBody Product product){
        productRepo.save(product);
        return product;
    }

    /**
     * Api endpoint to delete the product
     * @param itemId
     */
    @DeleteMapping(value = "/delete/{itemId}")
    public void deleteUser(@PathVariable("itemId") Integer itemId) {
        productRepo.delete(productRepo.getById(itemId));
    }

}
