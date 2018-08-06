package service;

import dao.OrderDetailMapper;
import dao.OrderMapper;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailExample;
import entity.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jimvio on 2018/7/18.
 */
@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;

    public List<Order> getAllOrders() {
        return orderMapper.selectByExample(null);
    }

    public List<Order> getOrdersByCondition(String condition) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Pattern pattern1 = Pattern.compile("[0-9a-zA-Z]*");
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if (pattern.matcher(condition).matches()){  //订单号
            criteria.andOrderIdEqualTo(Integer.parseInt(condition));
            return orderMapper.selectByExample(orderExample);
        }else if(pattern1.matcher(condition.substring(1)).matches()){   //车牌号
            criteria.andCarNumberEqualTo(condition);
            return orderMapper.selectByExample(orderExample);
        }else { //司机姓名
            criteria.andDriverNameEqualTo(condition);
            return orderMapper.selectByExample(orderExample);
        }
    }

    public List<OrderDetail> getOrderDetails(Integer orderId) {
        OrderDetailExample orderDetailExample = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderDetail> list = orderDetailMapper.selectByExample(orderDetailExample);
        return list;
    }
}
