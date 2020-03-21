package com.mark.demo.dto;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-21 18:35
 **/
@Data
public class HotTagDTO implements Comparable{

    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
