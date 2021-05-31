package com.miaoshaproject;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
//开启spring boot自动化部署,启动内嵌的tomcat
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
@RestController
@MapperScan("com.miaoshaproject.dao")
public class App {

    @Autowired
    private UserDOMapper userDOMapper;

    //自动完成mvc的配置
    @RequestMapping("/")
    public String home(){
        UserDO userDO=userDOMapper.selectByPrimaryKey(1);
        if(userDO==null){
            return "用户对象不存在";
        }else {
            return userDO.getName();
        }
    }
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);//自动化部署,启动了一个tomcat容器，在8080端口
    }
}
