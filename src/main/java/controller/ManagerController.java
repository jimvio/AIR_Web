package controller;

import entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ManagerService;
import utils.Msg;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jimvio on 2018/7/16.
 */

@Controller
public class ManagerController {
    @Autowired
    ManagerService managerService;

    /**
     * 登陆验证
     * @param manager 登陆用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg login(Manager manager, HttpServletRequest request){
        if(managerService.doLogin(manager)){
            request.getSession().setAttribute("status","yes");  //设置登陆状态
            request.getSession().setAttribute("userName",manager.getUsername());    //请求域中存入用户名
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    /**
     * 用户注册
     * @param manager 注册用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Msg register(Manager manager){
        if (managerService.doRegister(manager)){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }
}
