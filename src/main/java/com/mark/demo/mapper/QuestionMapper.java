package com.mark.demo.mapper;


import com.mark.demo.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {


    @Insert("INSERT into question(title,description,gmt_create,gmt_modified,creator,tag)VALUES(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void createQuestion(Question question);

    @Select("select * from question ORDER BY gmt_create DESC LIMIT #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question where creator = #{userId}  ORDER BY gmt_create DESC LIMIT #{offset},#{size}")
    List<Question> listByUserID(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("Select count(1) from question")
    Integer count();

    @Select("Select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("Select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);


    @Update("update question set title = #{title},description = #{description},tag = #{tag},gmt_modified = #{gmtModified} where id = #{id}")
    void updateQuestion(Question question);
}
