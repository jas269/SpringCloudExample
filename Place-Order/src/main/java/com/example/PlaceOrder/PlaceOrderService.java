package com.example.PlaceOrder;

import com.example.PlaceOrder.model.Order;
import com.example.PlaceOrder.model.Product;
import com.example.PlaceOrder.model.ProductDetail;
import com.example.PlaceOrder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Controller Class for the PlaceOrder Application
 */
@RestController
@RequestMapping("/placeorder")
public class PlaceOrderService {

    @Autowired
    private PlaceOrderClient placeOrderClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    /**
     * This api is used to place order
     * @param userId the unique userId generated while registering
     * @param list the list of items and quantity specified by the user
     * @return order details if order placed successfully.
     */
    @PostMapping("/order/{userId}")
    public String placeOrder(@PathVariable Integer userId, @RequestBody List<ProductDetail> list){
        try{
            User user = getUser(userId);
        }catch (Exception e){
            return "User not found";
        }
        for (ProductDetail p: list
        ) {
            String item=p.getItemName();
            System.out.println(item);
            Product product =getProductByName(item);
            if(p.getItemCount() > product.getItemCount()){
                return "Item"+p.getItemName()+"not available";
            }
        }

        rabbitMQConfig.send(new Order(userId,list));
        return "Order placed Successfully";
    }

    /**
     * @return List of registered users.
     */
    @GetMapping
    public List<User> getUsers() {
        ResponseEntity<List<User>> users = restTemplate.exchange
                ("http://userservice/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        return users.getBody();
    }

    /**
     * @param userId
     * @return the user details for a specific userId
     */
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Integer userId) {
        User user = restTemplate.getForObject("http://userservice/users/{userId}",User.class,userId);
        return user;
    }

    /**
     * @return The List of products available at the store
     */
    @GetMapping("/showAllProducts")
    public List<Product> getProduct(){
        return placeOrderClient.getAllProducts();
    }

    /**
     * @param itemName
     * @return the product details for the specific product name entered
     */
    @GetMapping("/product")
    public Product getProductByName(String itemName){
        Product product = restTemplate.getForObject("http://productdetails/products/itemName/{itemName}",Product.class,
                itemName);
        return product;
    }

    /**
     * @param itemNames List of itemNames
     * @return the product details of the all the items sent by the user.
     */
    @GetMapping("/productListDetails/{itemNames}")
    public List<Product> getProductsByNameList(@PathVariable List<String> itemNames){
        List<Product> products = new ArrayList<Product>();
        itemNames.forEach(itemName->{
            products.add(getProductByName(itemName));
        }) ;
        return products;
    }
}
