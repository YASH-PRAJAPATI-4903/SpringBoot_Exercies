package com.yash.controller;

import com.yash.dto.MerchantDTO;
import com.yash.dto.MerchantWithStoreDTO;
import com.yash.entity.Merchant;
import com.yash.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllMerchant(){
        if(merchantService.getAllMerchant().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(merchantService.getAllMerchant());
    }

    @GetMapping("/")
    public  ResponseEntity<?> getByNameContaining(@RequestParam(name = "name") String name){
        if(name == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Merchant> merchant = merchantService.getMerchantByName(name);
        if(merchant == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(merchant);

    }

    @PostMapping("/")
    public ResponseEntity<String> createMerchant(@RequestBody MerchantWithStoreDTO merchantWithStoreDTO){
        if(merchantWithStoreDTO==null || merchantWithStoreDTO.getMerchantStores().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        merchantService.createMerchant(merchantWithStoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable(name = "id") int id, @RequestBody MerchantDTO merchantDTO){

        if(merchantDTO.getName() == null && merchantDTO.getMobile() == null && merchantDTO.getEmail() == null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        if(merchantService.updateMerchant(id, merchantDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return  ResponseEntity.ok("Details updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") int id){
        merchantService.deleteByMerchantId(id);
        return ResponseEntity.ok("delete merchant by given id!!!!");
    }

    @DeleteMapping("/store/{id}")
    public ResponseEntity<String> deleteStoreByID(@PathVariable(name = "id") int id){
        String m =merchantService.removeStore(id);
        if(m==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(m);
    }

}
