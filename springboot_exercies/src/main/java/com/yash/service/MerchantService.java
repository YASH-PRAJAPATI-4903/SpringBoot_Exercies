package com.yash.service;

import com.yash.Repository.MerchantRepository;
import com.yash.Repository.MerchantStoreRepository;
import com.yash.dto.MerchantDTO;
import com.yash.dto.MerchantStoreDTO;
import com.yash.dto.MerchantWithStoreDTO;
import com.yash.entity.Merchant;
import com.yash.entity.MerchantStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService{

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantStoreRepository merchantStoreRepository;

    public List<Merchant> getAllMerchant(){
        return merchantRepository.findAll();
    }

    public List<Merchant> getMerchantByName(String name){
        return  merchantRepository.findByNameContaining(name);
    }

    public void createMerchant(MerchantWithStoreDTO merchantWithStoreDTO){
        Merchant merchant = new Merchant();
        merchant.setName(merchantWithStoreDTO.getMerchantDTO().getName());
        merchant.setMobile(merchantWithStoreDTO.getMerchantDTO().getMobile());
        merchant.setEmail(merchantWithStoreDTO.getMerchantDTO().getEmail());
        Merchant savemerchant =merchantRepository.save(merchant);

        for (MerchantStoreDTO m: merchantWithStoreDTO.getMerchantStores()){
            MerchantStore merchantStore = new MerchantStore();
            merchantStore.setStoreName(m.getStoreName());
            merchantStore.setActive(m.isActive());
            merchantStore.setMerchantId(savemerchant.getId());
            merchantStoreRepository.save(merchantStore);
        }

    }
    public boolean updateMerchant(int id, MerchantDTO merchantDTO){

        return merchantRepository.findById(id).map(ex-> {
            if(merchantDTO.getName()!= null){
                ex.setName(merchantDTO.getName());
            }
            if(merchantDTO.getEmail()!= null){
                ex.setEmail(merchantDTO.getEmail());
            }
            if(merchantDTO.getMobile()!= null){
                ex.setMobile(merchantDTO.getMobile());
            }
            return merchantRepository.save(ex);
        }).isEmpty();

    }

    public void deleteByMerchantId(int id){
        merchantRepository.deleteById(id);
    }

    public String removeStore(int id){
        if(merchantStoreRepository.findById(id).isEmpty()){
            return null;
        }
        merchantStoreRepository.deleteById(id);
        return "remove store by given id!!!!";
    }

}
