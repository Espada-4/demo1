package com.mark.demo.controller;

import com.mark.demo.dto.CommentDTO;
import com.mark.demo.dto.QuestionDTO;
import com.mark.demo.enums.CommentTypeEnum;
import com.mark.demo.service.CommentService;
import com.mark.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: demo
 * @description: 问题详情
 * @author: wu
 * @create: 2020-03-12 21:48
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public  String question(@PathVariable(name = "id") Long id,
                            Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        questionDTO.setTag("2");
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //增加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
