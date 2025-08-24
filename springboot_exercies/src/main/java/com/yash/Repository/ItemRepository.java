package com.yash.Repository;

import com.yash.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
//    Item findById(Long id);
}
