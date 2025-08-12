package com.yash.service;

import com.yash.Repository.CategoryRepository;
import com.yash.Repository.SubCategoryRepository;
import com.yash.dto.CategoryWithSubCategoryDTO;
import com.yash.entity.Category;
import com.yash.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public List<Category> getByCategoryName(String name){
        return categoryRepository.findByNameIgnoreCaseContaining(name);
    }

    public void createCategory(CategoryWithSubCategoryDTO categoryWithSubCategoryDTO){
        Category category= new Category();
        List<SubCategory> saveSub = new ArrayList<>();
        category.setName(categoryWithSubCategoryDTO.getName());
        for (String s: categoryWithSubCategoryDTO.getSubName()){
            SubCategory subCategory= new SubCategory();
            subCategory.setName(s);
            saveSub.add(subCategoryRepository.save(subCategory));
        }
        category.setSubCategoryList(saveSub);
        categoryRepository.save(category);
    }

    public Category updateCategory(int id, CategoryWithSubCategoryDTO categoryWithSubCategoryDTO){
        if(!categoryRepository.existsById(id) || categoryWithSubCategoryDTO.getName().isEmpty()){
            return null;
        }
        return categoryRepository.findById(id).map(ex->{
            ex.setName(categoryWithSubCategoryDTO.getName());
            return categoryRepository.save(ex);
        }).get();
    }

    public String deleteCategoryByID(int id){
        if(categoryRepository.findById(id).isEmpty()){
            return null;
        }
        categoryRepository.deleteById(id);
        return "delete Category by given id!!!!";
    }

}
