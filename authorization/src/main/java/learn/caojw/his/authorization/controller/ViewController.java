package learn.caojw.his.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图控制层
 *
 * @author 曹健伟
 */
@Controller
public class ViewController {
    @RequestMapping
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/authorize")
    public String authorize() {
        return "authorize";
    }
}
