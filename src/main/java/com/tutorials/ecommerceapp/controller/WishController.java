package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.CreateWishListDto;
import com.tutorials.ecommerceapp.dto.product.ProductDto;
import com.tutorials.ecommerceapp.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/wishlist/")
public class WishController {

    private final WishListService wishListService;

    @PostMapping("add-to-wishlist")
    public ResponseEntity<String> addToWishList(@Valid @RequestBody CreateWishListDto createWishListDto){

        wishListService.createWishList(createWishListDto);

        return ResponseEntity.ok().body("Product Added to WishList");
    }

    @GetMapping("get-wishlist-by-user")
    public ResponseEntity<List<ProductDto>> getWishList(){

        return ResponseEntity.ok().body(wishListService.getWishList());
    }

    @DeleteMapping("remove-item-from-wishlist/{wishListId}")
    public ResponseEntity<String> removeFromWishList(@PathVariable("wishListId") Long wishListId){

        wishListService.removeFromWishList(wishListId);

        return ResponseEntity.ok().body("Item removed from WishList");
    }

    @DeleteMapping("clear-wishlist")
    public ResponseEntity<String> clearWishList(){
        wishListService.clearWishList();
        return ResponseEntity.ok().body("WishList has been emptied");
    }
}
