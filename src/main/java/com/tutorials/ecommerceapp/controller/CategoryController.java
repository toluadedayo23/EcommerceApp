package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.CategoryDto;
import com.tutorials.ecommerceapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Catgory has ben created");
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body("Category: " + categoryDto.getCategoryName() + " successfully updated");
    }

}
