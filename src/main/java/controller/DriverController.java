package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DriverService;
import utils.Msg;

import java.util.List;

/**
 * Created by jimvio on 2018/7/18.
 */
@Controller
public class DriverController {
    @Autowired
    DriverService driverService;

    /**
     * 司机信息分页查询函数
     * @param pn 查询页码
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllDrivers")
    public Msg getAllDrivers(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,10);
        List<Driver> drivers = driverService.getAllDrivers();
        PageInfo pageInfo = new PageInfo(drivers,5);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 搜索框查询司机信息
     * @param condition 搜索内容
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDriversByCondition")
    public Msg getDriversByCondition(@RequestParam("condition") String condition){
        if(condition.isEmpty()){
            return Msg.empty();
        }else {
            PageHelper.startPage(1,10);
            List<Driver> drivers = driverService.getDriversByCondition(condition);
            if(drivers.isEmpty()){
                return Msg.empty();
            }else {
                PageInfo pageInfo = new PageInfo(drivers,3);
                return Msg.success().add("pageInfo",pageInfo);
            }
        }
    }
}
