package com.yash.Repository;

import com.yash.entity.Merchant;
import com.yash.entity.MerchantStore;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantStoreRepository extends JpaRepository<MerchantStore, Integer> {
    List<MerchantStore> findByStoreNameIgnoreCaseContaining(String Name);
//    @EntityGraph(attributePaths = {"Merchant", "item", "Item.Product", "Item.Product.SubCategory", "Item.Product.SubCategory.Category"})
//    MerchantStore findById(Long id);

}
