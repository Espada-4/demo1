package com.mark.demo.mapper;

import com.mark.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("Insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{account_id},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM user")
    List<User> getAll();

    @Select("SELECT * FROM user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT * FROM user where id = #{id}")
    User findById(@Param("id")Integer id);
}
