package xyz.leefly.project.web.controller.user;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.leefly.project.bo.User;
import xyz.leefly.project.common.utils.EncryptUtil;
import xyz.leefly.project.service.UserService;
import xyz.leefly.project.web.model.RespData;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public RespData<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        RespData<User> resp = new RespData<>();
        resp.setData(user);
        return resp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}")
    public RespData<User> getUserByName(@PathVariable("name") String name) {
        User user = userService.getUserByUsername(name);
        RespData<User> resp = new RespData<>();
        resp.setData(user);
        return resp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public RespData<Boolean> update() {
        RespData<Boolean> resp = new RespData<>();
        Page<User> page = userService.selectUsersByPage(1, 10);
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            for (User user : page.getRecords()) {
                user.setPassword(EncryptUtil.encryptPassword("123456"));
                userService.updateUser(user);
            }
        }
        userService.registerUser("admin", "admin");
        resp.setData(true);
        return resp;
    }

}
