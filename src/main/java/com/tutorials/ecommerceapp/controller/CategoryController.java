package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.CategoryDto;
import com.tutorials.ecommerceapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category/")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category has been created");
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body("Category: " + categoryDto.getCategoryName() + " successfully updated");
    }

}
