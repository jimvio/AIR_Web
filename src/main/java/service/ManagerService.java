package service;

import dao.EmployeeMapper;
import dao.ManagerMapper;
import dao.RightMapper;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jimvio on 2018/7/16.
 */
@Service
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    RightMapper rightMapper;


    public boolean doLogin(Manager manager) {
        RightExample rightExample = new RightExample();
        RightExample.Criteria criteria = rightExample.createCriteria();
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria1 = managerExample.createCriteria();
        criteria.andUsernameEqualTo(manager.getUsername());
        criteria1.andUsernameEqualTo(manager.getUsername()).andPasswordEqualTo(manager.getPassword());
        List<Right> list = rightMapper.selectByExample(rightExample);
        List<Manager> list1 = managerMapper.selectByExample(managerExample);
        if(!list.isEmpty()&&!list1.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public boolean doRegister(Manager manager) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmployeeNumberEqualTo(manager.getEmployeeNumber());
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria1 = managerExample.createCriteria();
        criteria1.andUsernameEqualTo(manager.getUsername());
        if (!employeeMapper.selectByExample(employeeExample).isEmpty()&&managerMapper.selectByExample(managerExample).isEmpty()){
            managerMapper.insert(manager);
            return true;
        }else {
            return false;
        }
    }
}
