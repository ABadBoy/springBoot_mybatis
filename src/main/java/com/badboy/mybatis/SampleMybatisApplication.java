package com.badboy.mybatis;

import com.badboy.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.badboy.mapper")
public class SampleMybatisApplication  implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;



    public static void main(String[] args) {
        SpringApplication.run(SampleMybatisApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.userMapper.findByName("peter"));
    }
}
