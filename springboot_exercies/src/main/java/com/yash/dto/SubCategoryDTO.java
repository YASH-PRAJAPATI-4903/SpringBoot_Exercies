package com.yash.dto;

import com.yash.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryDTO {
    private int id;
    private String name;
    private CategoryDTO categoryDTO;
}
