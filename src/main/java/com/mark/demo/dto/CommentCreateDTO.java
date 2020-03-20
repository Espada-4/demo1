package com.mark.demo.dto;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-14 20:36
 **/
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;

}
