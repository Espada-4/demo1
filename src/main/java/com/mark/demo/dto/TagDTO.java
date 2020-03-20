package com.mark.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-17 17:43
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
