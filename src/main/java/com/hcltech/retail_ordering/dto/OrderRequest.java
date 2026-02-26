package com.hcltech.retail_ordering.dto;

import lombok.*;

@Getter
@Setter
public class OrderRequest {
    private Long menuId;
    private Integer quantity;
}
