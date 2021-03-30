package cn.itcast.service;

//import cn.itcast.dao.TkUserMapper;
import cn.itcast.dao.UserMapper;
//import cn.itcast.pojo.TkUserPojo;
import cn.itcast.pojo.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void insertUser(User user){
        userMapper.insert(user);
    }

    public List<User> queryAll(User user){
        PageHelper.startPage(user.getPage(),user.getSize());
        return userMapper.selectAll();
    }
}
