package com.mark.demo.dto;


import com.mark.demo.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Long id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
