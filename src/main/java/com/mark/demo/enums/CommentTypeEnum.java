package com.mark.demo.enums;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-14 23:01
 **/
public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2)
    ;
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType() == type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
}
