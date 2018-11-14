package com.example.lesson.controller;

import com.example.lesson.entity.UserEntity;
import com.example.lesson.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserJPA userJPA;

    /**
     * 查询用户列表方法
     * @return
     */
    @RequestMapping(value ="/list" ,method = RequestMethod.GET)
    public List<UserEntity> list(){
        return userJPA.findAll();
    }

    /**
     * 添加、更新用户方法
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public UserEntity save(HttpServletRequest request)
    {
        String name = request.getParameter("name");
        UserEntity entity = new UserEntity();
        entity.setName(name);
        return userJPA.save(entity);
    }

    /**
     * 删除用户方法
     * @param id 用户编号
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<UserEntity> delete(Long id)
    {
        userJPA.delete(id);
        return userJPA.findAll();
    }

    /**
     * 登录
     */

    @RequestMapping(value = "/login")
    public String login(UserEntity user, HttpServletRequest request)
    {
        Boolean flag = true;
        String result =  "登录成功";
        // 根据用户名查询用户是否存在
        UserEntity userEntity = userJPA.findOne(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"), user.getName()));
                return null;
            }
        });

        // 用户不存在
        if (userEntity == null) {
            flag = false;
            result = "用户不存在";
        }
        // 密码错误
        else if (!userEntity.getPwd().equals(user.getPwd())) {
            result = "密码不正确，登录失败";
            flag = false;
        }
        // 登陆成功
        if (flag) {
            request.getSession().setAttribute("_session_user", userEntity);
        }
        return result;
    }
}
