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

    public Category createCategory(CategoryWithSubCategoryDTO categoryWithSubCategoryDTO){
        Category category= new Category();
        List<SubCategory> saveSub = new ArrayList<>();
        category.setName(categoryWithSubCategoryDTO.getName());
        for (String s: categoryWithSubCategoryDTO.getSubName()){
            SubCategory subCategory= new SubCategory();
            subCategory.setName(s);
            saveSub.add(subCategory);
        }
        category.setSubCategoryList(saveSub);
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, CategoryWithSubCategoryDTO categoryWithSubCategoryDTO){
        if(!categoryRepository.existsById(id) || categoryWithSubCategoryDTO.getName().isEmpty()){
            return null;
        }
        return categoryRepository.findById(id).map(ex->{
            if(categoryWithSubCategoryDTO.getName()!=null){
                ex.setName(categoryWithSubCategoryDTO.getName());
            }
            if(categoryWithSubCategoryDTO.getSubName()!= null){
                for(String i : categoryWithSubCategoryDTO.getSubName()) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.setName(i);
                    ex.getSubCategoryList().add(subCategoryRepository.save(subCategory));
                }
            }
            return categoryRepository.save(ex);
        }).get();
    }

//    public Category addSubCategory(int id, SubCategory subCategory){
//        if(!categoryRepository.existsById(id) ||  subCategory.getName().isEmpty()){
//            return null;
//        }
////        if(subCategoryRepository.existsById(subCategory.getId())){
////            return subCategoryRepository.findById(id).map(ex->{
////                ex.setName(subCategory.getName());
////                return subCategoryRepository.save(ex);
////            }).get();
////        }7
//        return categoryRepository.findById(id).map(ex-> {
//            ex.getSubCategoryList().add(subCategory);
//            return categoryRepository.save(ex);
//        }).get();
//    }

    public String deleteCategoryByID(int id){
        if(categoryRepository.findById(id).isEmpty()){
            return null;
        }
        categoryRepository.deleteById(id);
        return "delete Category by given id!!!!";
    }

}
