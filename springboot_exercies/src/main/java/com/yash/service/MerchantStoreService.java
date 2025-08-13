package com.yash.service;

import com.yash.Repository.*;
import com.yash.dto.*;
import com.yash.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MerchantStoreService {

    @Autowired
    private MerchantRepository merchantRepository;
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

    public List<MerchantStore> getStoreByName(String name){
        return  merchantStoreRepository.findByStoreNameIgnoreCaseContaining(name);
    }

    public Optional<MerchantStore> getStoreById(int id){
        return  merchantStoreRepository.findById(id);
    }

    public StoreWithDetails getStoreDetailedById(int id){
        List<ItemDto> itemDtos = new ArrayList<>();

        StoreWithDetails store=new StoreWithDetails();
//        ItemWithAllDetailsDTO itemWithAllDetailsDTO= new ItemWithAllDetailsDTO();
//        if(!itemRepository.existsById(id)){
//            return Optional.empty();
//        }
//        Optional<Item> item= itemRepository.findById(id);
        Optional<MerchantStore> merchantStore = merchantStoreRepository.findById(id);
        if(merchantStore.isPresent()) {
            store.setStoreName(merchantStore.get().getStoreName());
            store.setActive(merchantStore.get().isActive());
//            store.set
            for(Item i: merchantStore.get().getItemList()){
                ItemDto itemDto=new ItemDto();
                itemDto.setId(i.getId());
                itemDto.setItemPrice(i.getItemPrice());
                itemDto.setQuantity(i.getQuantity());
                Optional<Product> product=productRepository.findById(i.getProductId());
                if(product.isPresent()) {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(product.get().getId());
                    productDTO.setName(product.get().getName());
                    productDTO.setProductBrand(product.get().getProductBrand());
                    Optional<SubCategory> subCategory = subCategoryRepository.findById(product.get().getSubCategoryID());
                    if (subCategory.isPresent()) {
                        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
                        subCategoryDTO.setId(product.get().getSubCategoryID());
                        subCategoryDTO.setName(subCategory.get().getName());
                        Optional<Category> category= categoryRepository.findById(subCategory.get().getCategoryId());
                        if(category.isPresent()){
                            CategoryDTO categoryDTO=new CategoryDTO();
                            categoryDTO.setId(category.get().getId());
                            categoryDTO.setName(category.get().getName());
                            subCategoryDTO.setCategoryDTO(categoryDTO);
                        }
                        productDTO.setSubCategoryDTO(subCategoryDTO);
                    }
                    itemDto.setProductDTO(productDTO);
                }

                itemDtos.add(itemDto);
            }
            store.setItemDtoList(itemDtos);

            Optional<Merchant> merchant = merchantRepository.findById(merchantStore.get().getMerchantId());
            if(merchant.isPresent()){
                MerchantDTO merchantDTO= new MerchantDTO();
                merchantDTO.setName(merchant.get().getName());
                merchantDTO.setMobile(merchant.get().getMobile());
                merchantDTO.setEmail(merchant.get().getEmail());
                store.setMerchantDTO(merchantDTO);
            }
        }else{
            return null;
        }

        return store;
    }


    public MerchantStore createMerchantStore(MerchantStore merchantStore){
        if(!merchantRepository.existsById(merchantStore.getMerchantId())){
            return null;
        }
        return merchantStoreRepository.save(merchantStore);
    }
    public String updateMerchantStore(int id, MerchantStore merchantStore){
//        if(!merchantStoreRepository.existsById(merchantStore.getMerchantId())){
//            return "";
//        }
        merchantStoreRepository.findById(id).map(ex-> {
//            if(merchantStoreRepository.existsById(merchantStore.getMerchantId())){
//                ex.setMerchantId(merchantStore.getMerchantId());
//            }
            if(merchantStore.getStoreName()!= null){
                ex.setStoreName(merchantStore.getStoreName());
            }
            ex.setActive(merchantStore.isActive());
            return merchantStoreRepository.save(ex);
        }).get();
        return "Store updated successfully";
    }

    public String deleteByMerchantId(int id) {
        if(!merchantStoreRepository.existsById(id)){
            return "";
        }
        merchantStoreRepository.deleteById(id);
        updateMerchantStore(id, null);
        return "delete merchant Store by given id!!!!";
    }
}
