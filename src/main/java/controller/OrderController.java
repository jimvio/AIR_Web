package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Order;
import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrderService;
import utils.Msg;

import java.util.List;

/**
 * Created by jimvio on 2018/7/18.
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;


    /**
     * 订单分页查询函数
     * @param pn 查询页码
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllOrders")
    public Msg getAllOrders(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,10);
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()){
            return Msg.empty();
        }else {
            PageInfo pageInfo = new PageInfo(orders,5);
            return Msg.success().add("pageInfo",pageInfo);
        }
    }

    /**
     * 搜索框查询订单信息
     * @param condition 搜索内容
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrderByCondition")
    public Msg getOrdersByCondition(@RequestParam("condition") String condition){
        if (condition.isEmpty()){
            return Msg.empty();
        }else{
            PageHelper.startPage(1,10);
            List<Order> orders = orderService.getOrdersByCondition(condition);
            if(orders.isEmpty()){
                return Msg.empty();
            }else {
                PageInfo pageInfo = new PageInfo(orders,3);
                return Msg.success().add("pageInfo",pageInfo);
            }
        }
    }

    @ResponseBody
    @RequestMapping("/getOrderDetails")
    public Msg getOrderDetails(@RequestParam("orderId") Integer orderId){
        PageHelper.startPage(1,10);
        List<OrderDetail> orderDetails = orderService.getOrderDetails(orderId);
        if (orderDetails.isEmpty()){
            return Msg.empty();
        }else {
            PageInfo pageInfo = new PageInfo(orderDetails,3);
            return Msg.success().add("pageInfo",pageInfo);
        }
    }
}
