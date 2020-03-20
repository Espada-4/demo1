package com.mark.demo.dto;

import com.mark.demo.model.User;
import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-15 22:45
 **/
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
    private Integer commentCount;
}
