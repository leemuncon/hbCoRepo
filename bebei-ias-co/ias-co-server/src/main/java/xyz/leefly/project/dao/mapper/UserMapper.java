package xyz.leefly.project.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import xyz.leefly.project.bo.User;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    Long registerUser(String username, String password);

    /**
     * 跟新用户
     * @param user
     */
    int updateUser(User user);

    /**
     * 检查用户名与密码
     * @param username
     * @param password
     * @return
     */
    boolean checkUserByUsernameAndPassword(String username, String password);

    /**
     * 逻辑删除用户
     * @param id
     * @return
     */
    int deleteUserLogic(Long id);

}
