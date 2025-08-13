package com.yash.controller;

import com.yash.dto.MerchantDTO;
import com.yash.dto.StoreWithDetails;
import com.yash.entity.MerchantStore;
import com.yash.service.MerchantService;
import com.yash.service.MerchantStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class MerchantStoreController {

    @Autowired
    private MerchantStoreService merchantStoreService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MerchantStore>> getByStoreId(@PathVariable(name="id") int id){
        Optional<MerchantStore> merchantStore= merchantStoreService.getStoreById(id);
        if(merchantStore.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(merchantStore);
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<StoreWithDetails> getByStoreDId(@PathVariable(name="id") int id){
        StoreWithDetails storeWithDetails= merchantStoreService.getStoreDetailedById(id);
        if(storeWithDetails==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(storeWithDetails);
    }

    @GetMapping("/")
    public ResponseEntity<List<MerchantStore>> getByStoreName(@RequestParam(name="name") String name){
        List<MerchantStore> merchantStores=merchantStoreService.getStoreByName(name);
        if(merchantStores.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(merchantStores);
    }

    @PostMapping("/")
    public ResponseEntity<String> createMerchantStore(@RequestBody MerchantStore merchantStore){
        if(merchantStore==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        MerchantStore merchantStoreSave = merchantStoreService.createMerchantStore(merchantStore);
        if(merchantStoreSave==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Merchant Not found by given id.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("New store created!!!");
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable(name = "id") int id, @RequestBody MerchantStore merchantStore){

        if(merchantStore == null ){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        String m = merchantStoreService.updateMerchantStore(id, merchantStore);
        if(m.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No merchant find by given id.");
        }
        return  ResponseEntity.ok(m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") int id){
        String m=merchantStoreService.deleteByMerchantId(id);
        if(m.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(m);
    }

}
