package com.yash.service;

import com.yash.Repository.*;
import com.yash.dto.*;
import com.yash.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private MerchantStoreRepository merchantStoreRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Item> getItemById(Long id){
        return  itemRepository.findById(id);
    }

    public ItemWithAllDetailsDTO getItemByIdWithAllDetails(Long id){


        ItemWithAllDetailsDTO itemWithAllDetailsDTO= new ItemWithAllDetailsDTO();
//        if(!itemRepository.existsById(id)){
//            return Optional.empty();
//        }
        Optional<Item> item= itemRepository.findById(id);

        if(item.isPresent()) {
            itemWithAllDetailsDTO.setId(id);
            itemWithAllDetailsDTO.setItemPrice(item.get().getItemPrice());
            itemWithAllDetailsDTO.setQuantity(item.get().getQuantity());
            Optional<MerchantStore> merchantStore= merchantStoreRepository.findById(item.get().getStoreId());
            if(merchantStore.isPresent()){
                MerchantStoreDTO merchantStoreDTO=new MerchantStoreDTO();
                merchantStoreDTO.setStoreName(merchantStore.get().getStoreName());
                merchantStoreDTO.setActive(merchantStore.get().isActive());
                itemWithAllDetailsDTO.setMerchantStoreDTO(merchantStoreDTO);
            }
            Optional<Product> product=productRepository.findById(item.get().getProductId());
            if(product.isPresent()){
                ProductDTO productDTO= new ProductDTO();
                productDTO.setId(product.get().getId());
                productDTO.setName(product.get().getName());
                productDTO.setProductBrand(product.get().getProductBrand());
                Optional<SubCategory> subCategory = subCategoryRepository.findById(product.get().getSubCategoryID());
                if(subCategory.isPresent()){
                    SubCategoryDTO subCategoryDTO= new SubCategoryDTO();
                    subCategoryDTO.setId(product.get().getSubCategoryID());
                    subCategoryDTO.setName(subCategoryDTO.getName());
//                    Optional<Category> category= categoryRepository.findById(Integer.parseInt(cid));
//                    if(category.isPresent()){
//                        CategoryDTO categoryDTO=new CategoryDTO();
//                        categoryDTO.setId(category.get().getId());
//                        categoryDTO.setName(category.get().getName());
//                        subCategoryDTO.setCategoryDTO(categoryDTO);
//                    }
                    productDTO.setSubCategoryDTO(subCategoryDTO);
                }
                itemWithAllDetailsDTO.setProductDTO(productDTO);
            }
        }else{
            return null;
        }

        return itemWithAllDetailsDTO;
    }


    public Item createItem(Item item){
        if(!merchantStoreRepository.existsById(item.getStoreId()) || !productRepository.existsById(item.getProductId())){
            return null;
        }
        return itemRepository.save(item);
    }
    public String updateItem(Long id, Item item){
//        if(!merchantStoreRepository.existsById(item.getStoreId()) || !productRepository.existsById(item.getProductId())){
//            return "";
//        }
        itemRepository.findById(id).map(ex-> {
//            if(merchantStoreRepository.existsById(item.getStoreId())){
//                ex.setStoreId(item.getStoreId());
//            }
//            if(productRepository.existsById(item.getProductId())){
//                ex.setProductId(item.getProductId());
//            }
            if(item.getItemPrice()!= null){
                ex.setItemPrice(item.getItemPrice());
            }
            if(item.getQuantity()!= null){
                ex.setQuantity(item.getQuantity());
            }
            return itemRepository.save(ex);
        }).get();
        return "item updated successfully";
    }

    public String deleteByItemId(Long id) {
        if(!itemRepository.existsById(id)){
            return null;
        }
        itemRepository.deleteById(id);
        return "delete item by given id!!!!";
    }

}
