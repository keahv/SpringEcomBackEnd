package com.example.demo.repo;

import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query("SELECT c FROM Cart c JOIN c.product p WHERE c.user.id = :userId")
    List<Cart> findCartItemsByUserId(int userId);

    @Query("SELECT c FROM Cart c JOIN c.product p WHERE "+
            "(c.user.id = :userId AND LOWER(p.name) LIKE LOWER(CONCAT('%' , :keyword ,'%'))) OR" +
            "(c.user.id = :userId AND LOWER(p.name) LIKE LOWER(CONCAT('%' , :keyword ,'%'))) OR" +
            "(c.user.id = :userId AND LOWER(p.name) LIKE LOWER(CONCAT('%' , :keyword ,'%'))) OR" +
            "(c.user.id = :userId AND LOWER(p.name) LIKE LOWER(CONCAT('%' , :keyword ,'%'))) "
     )
    List<Cart> searchCartItems(int userId,String keyword);

}
