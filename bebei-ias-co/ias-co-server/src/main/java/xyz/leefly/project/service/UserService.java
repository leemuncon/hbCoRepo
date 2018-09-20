package xyz.leefly.project.service;

import com.baomidou.mybatisplus.plugins.Page;
import xyz.leefly.project.bo.User;

public interface UserService {

    User getUserById(Long id);

    User getUserByUsername(String name);

    Long registerUser(String username, String password);

    Boolean updateUser(User user);

    Boolean checkUserByUsernameAndPassword(String username, String password);

    Page<User> selectUsersByPage(int pageNo, int pageSize);


}
