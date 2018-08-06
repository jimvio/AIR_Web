package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RightService;
import utils.Msg;

import java.util.List;

/**
 * Created by jimvio on 2018/7/18.
 */
@Controller
public class RightController {
    @Autowired
    RightService rightService;

    /**
     * 权限分页查询函数
     * @param pn 查询页数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllRights")
    public Msg getAllRights(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,10);
        List<Right> rights = rightService.getAllRights();
        PageInfo pageInfo = new PageInfo(rights,5);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 搜索框查询权限
     * @param condition 查询内容
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRightByCondition")
    public Msg getRightByCondition(@RequestParam("condition") String condition){
        if (condition.isEmpty()){
            return Msg.empty();
        }else{
            PageHelper.startPage(1,10);
            List<Right> rights = rightService.getRightsByCondition(condition);
            if(rights.isEmpty()){
                return Msg.empty();
            }else {
                PageInfo pageInfo = new PageInfo(rights,3);
                return Msg.success().add("pageInfo",pageInfo);
            }
        }

    }

    /**
     * 权限添加
     * @param username 所添加的用户名
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRight")
    public Msg addRight(@RequestParam("username") String username){
        if(rightService.addRight(username)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }


    /**
     * 权限删除
     * @param username 删除的用户名
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteRight")
    public Msg DeleteRight(@RequestParam("username") String username){
        if(rightService.deleteRight(username)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

}
