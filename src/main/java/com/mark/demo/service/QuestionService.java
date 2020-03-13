package com.mark.demo.service;

import com.mark.demo.dto.PaginationDTO;
import com.mark.demo.dto.QuestionDTO;
import com.mark.demo.mapper.QuestionMapper;
import com.mark.demo.mapper.UserMapper;
import com.mark.demo.model.Question;
import com.mark.demo.model.QuestionExample;
import com.mark.demo.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        if (size <= 0) size = 5;
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample example = new QuestionExample();
        Integer totalCount = (int)questionMapper.countByExample(example);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) page = totalPage;
        if (page < 1) page = 1;
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);

        example.setOrderByClause("gmt_create desc");
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);


        return paginationDTO;
    }

    public PaginationDTO listByUserID(Integer userId, Integer page, Integer size) {
        if (size <= 0) size = 5;
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();


        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(example);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) page = totalPage;
        if (page < 1) page = 1;
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);

        QuestionExample exampled = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(exampled,new RowBounds(offset, size));

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);


        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return  questionDTO;
    }

    public void createOrUpdateQuestion(Question question) {
        if (question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setId(question.getId());
            questionMapper.updateByPrimaryKeySelective(updateQuestion);
        }
    }
}
