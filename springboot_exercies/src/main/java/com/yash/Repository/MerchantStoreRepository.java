package com.yash.Repository;

import com.yash.entity.MerchantStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantStoreRepository extends JpaRepository<MerchantStore, Integer> {

}
