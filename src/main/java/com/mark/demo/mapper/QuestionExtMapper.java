package com.mark.demo.mapper;

import com.mark.demo.dto.QuestionQueryDTO;
import com.mark.demo.model.Question;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-18 11:00
 **/
public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);

}
