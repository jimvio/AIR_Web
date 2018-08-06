package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.EmployeeService;
import utils.Msg;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Text;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by jimvio on 2018/7/16.
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    /**
     * 员工信息分页查询函数
     * @param pn 查询的页码
     * @return
     */
    @ResponseBody
    @RequestMapping("/getEmployees")
    public Msg getAllEmployees(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,10);
        List<Employee> employees = employeeService.getAllEmployees();
        PageInfo pageInfo = new PageInfo(employees,5);
        return Msg.success().add("pageInfo",pageInfo);
    }


    /**
     * 搜索框查询员工信息
     * @param condition 搜索内容
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEmployeesByCondition")
    public Msg getEmployeesByCondition(@RequestParam("condition") String condition) {
        if(condition.isEmpty()){
            System.out.println("条件为空");
            return Msg.empty();
        }else {
            PageHelper.startPage(1,10);
            List<Employee> list = employeeService.getEmployeesByCondition(condition);
            if (list.isEmpty()){
                return Msg.empty();
            }else{
                PageInfo pageInfo = new PageInfo(list,3);
                return Msg.success().add("pageInfo",pageInfo);
            }
        }
    }

}
