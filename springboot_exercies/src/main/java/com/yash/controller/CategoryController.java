package com.yash.controller;

import com.yash.Repository.CategoryRepository;
import com.yash.dto.CategoryWithSubCategoryDTO;
import com.yash.entity.Category;
import com.yash.entity.MerchantStore;
import com.yash.entity.SubCategory;
import com.yash.service.CategoryService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory(){
        List<Category> categories= categoryService.getAllCategory();
        if(categories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCategoryByName(@RequestParam(name = "name") String name){
        if(name==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(categoryService.getByCategoryName(name));
    }

    @PostMapping("/")
    public ResponseEntity<String> createCategory(@RequestBody CategoryWithSubCategoryDTO categoryWithSubCategoryDTO){
        if(categoryWithSubCategoryDTO == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Category category= categoryService.createCategory(categoryWithSubCategoryDTO);

        HttpHeaders responceHeaders= new HttpHeaders();
        responceHeaders.set("category-id", String.valueOf(category.getId()));
        responceHeaders.set("sub-category-ids", String.valueOf(category.getSubCategoryList().stream().map(SubCategory::getId).collect(Collectors.toList())));
        Cookie cookie= new Cookie("category-id", "cid=".concat( String.valueOf(category.getId())));
        Cookie cookie1=new Cookie("sub-category-ids", cookie.getValue().concat(":scid=").concat(String.valueOf(category.getSubCategoryList().stream().map(SubCategory::getId).collect(Collectors.toList()))));

        responceHeaders.set("set-cookie", cookie1.getValue());

        return new ResponseEntity<>("new Category generated.", responceHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable(name = "id")int id, @RequestBody CategoryWithSubCategoryDTO categoryWithSubCategoryDTO){
        Category category= categoryService.updateCategory(id,categoryWithSubCategoryDTO);
        if(category==null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.ok("Category updated Successfully!!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryByID(@PathVariable(name = "id") int id){
        String m =categoryService.deleteCategoryByID(id);
        if(m==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(m);
    }
}
