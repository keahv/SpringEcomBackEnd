package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService service){
        this.cartService = service;
    }

    @RequestMapping("/greet")
    public String greet(){
        return "hello Cart";
    }

    @PostMapping("")
    public ResponseEntity<?> addToCart(@RequestBody Cart cart){
        System.out.println(cart.toString());
        Cart cart1 = cartService.addToCart(cart);
        Map<String,Object> resp = new HashMap<>();
        if (cart1 != null){
            resp.put("msg","Added Successfully");
            resp.put("status",true);
            resp.put("data",cart1);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.put("msg","Failed to add");
        resp.put("status",false);
        return new ResponseEntity<>(resp, HttpStatus.EXPECTATION_FAILED);
    }

  @GetMapping("/{userId}")
  public ResponseEntity<?> getCartItemsByUserId(@PathVariable int userId){
      List<Cart> carts = cartService.viewCart(userId);
      return new ResponseEntity<>(carts,HttpStatus.OK);
  }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartItemById(@PathVariable int id){
        boolean success = cartService.deleteCartById(id);
       Map<String,Object> resp = new HashMap<>();
        if (success){
            resp.put("msg","Deleted Successfully");
            resp.put("status",true);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
       resp.put("msg","Failed to Delete");
       resp.put("status",false);
       return new ResponseEntity<>(resp, HttpStatus.EXPECTATION_FAILED);
   }

   @GetMapping("/search/{id}")
    public ResponseEntity<List<Product>> searchCartItems(@PathVariable int id, @RequestParam String keyword){
       System.out.println("searching with "+keyword);
       List<Product> cartList = cartService.searchCartItems(id,keyword).stream()
               .map((Cart::getProduct))
               .collect(Collectors.toList());

       return new ResponseEntity<>(cartList,HttpStatus.OK);
   }

}
