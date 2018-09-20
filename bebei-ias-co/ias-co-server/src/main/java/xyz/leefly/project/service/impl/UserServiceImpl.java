package xyz.leefly.project.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.leefly.project.bo.User;
import xyz.leefly.project.common.utils.EncryptUtil;
import xyz.leefly.project.dao.mapper.UserMapper;
import xyz.leefly.project.service.UserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Long registerUser(String username, String password) {
        return userMapper.registerUser(username, password);
    }

    @Override
    public Boolean updateUser(User user) {
        int row = userMapper.updateUser(user);
        return row > 0;
    }

    @Override
    public Boolean checkUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return EncryptUtil.checkPassword(password, user.getPassword());
    }

    @Override
    public Page<User> selectUsersByPage(int pageNo, int pageSize) {
        Page<User> page = new Page<>(pageNo, pageSize);
        page.setRecords(userMapper.selectPage(page, null));
        return page;
    }
}
