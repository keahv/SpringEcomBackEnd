package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository repository){
        this.cartRepository = repository;
    }


    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public boolean deleteCartById(int id) {
        Cart cart = cartRepository.getReferenceById(id);
        if (cart != null) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Cart> viewCart(int userId) {
        return cartRepository.findCartItemsByUserId(userId);
    }

    public List<Cart> searchCartItems(int userId,String keyword) {
        return cartRepository.searchCartItems(userId,keyword);
    }
}
