package com.mark.demo.dto;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-18 14:12
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;

}
