package xyz.leefly.project.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.leefly.project.web.config.WebConfigurer;
import xyz.leefly.project.web.model.RespData;
import xyz.leefly.project.web.utils.WebHelper;

@Controller
@RequestMapping("/")
public class BaseController {

    @Autowired
    private WebConfigurer webConfigurer;

    @RequestMapping(value = {"", "index", "home"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        Object userName = WebHelper.getRequest().getSession().getAttribute("userName");
        if (userName != null) {
            return "redirect:/index";
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public RespData login(String userName, String password) {
        RespData resp = new RespData();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            resp.setSuccess(false);
        } else {
            if (webConfigurer.getName().equals(userName)
                    && webConfigurer.getPassword().equals(password)) {
                resp.setSuccess(true);
                WebHelper.getRequest().getSession().setAttribute("userName", userName);
            }
        }
        return resp;
    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        // todo
        return "index";
    }

}
