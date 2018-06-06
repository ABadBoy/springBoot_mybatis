package com.badboy.mapper;

import com.badboy.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where name=#{name}")
    User findByName(@Param("name") String name);

    @Insert("insert into user(name,age) values(#{name},#{age})")
    void insert(User user);


    @Select("select * from user")
    List<User> selectAll();
}
