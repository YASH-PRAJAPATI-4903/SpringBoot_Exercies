package com.yash.controller;

import com.yash.Repository.ItemRepository;
import com.yash.dto.ItemWithAllDetailsDTO;
import com.yash.entity.Item;
import com.yash.entity.MerchantStore;
import com.yash.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Item>> getByItemId(@PathVariable(name="id") Long id){
        Optional<Item> item= itemService.getItemById(id);
        if(item.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(item);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<ItemWithAllDetailsDTO> getByItemIdWithAllDetails(@PathVariable(name="id") Long id){
        ItemWithAllDetailsDTO itemWithAllDetailsDTO= itemService.getItemByIdWithAllDetails(id);
        if(itemWithAllDetailsDTO== null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(itemWithAllDetailsDTO);
    }

    @GetMapping("/string")
    public String getStringWeb(){
        return "Hello world!!";
    }

    @PostMapping("/")
    public ResponseEntity<String> createItem(@RequestBody Item item){
        if(item==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        Item item1 = itemService.createItem(item);
        if(item1==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("store or product Not found by given id.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("New item created!!!");
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable(name = "id") Long id, @RequestBody Item item){

        if(item == null ){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        String m = itemService.updateItem(id, item);
        if(m.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Item find by given id.");
        }
        return  ResponseEntity.ok(m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
        String m=itemService.deleteByItemId(id);
        if(m==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(m);
    }
}
