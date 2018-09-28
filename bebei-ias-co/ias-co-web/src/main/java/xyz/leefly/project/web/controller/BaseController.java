package xyz.leefly.project.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "redirect:/list";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        Object userName = WebHelper.getRequest().getSession().getAttribute("userName");
        if (userName != null) {
            return "redirect:/index";
        }
        return "index";
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

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "list";
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public String save() {
        return "save";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String save(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "save";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "detail";
    }

}
