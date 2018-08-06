package service;

import dao.EmployeeMapper;
import entity.Employee;
import entity.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jimvio on 2018/7/16.
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees() {
        return employeeMapper.selectByExample(null);
    }

    /**
     * 员工查询
     * @param condition 条件
     * @return
     */
    public List<Employee> getEmployeesByCondition(String condition) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        Pattern pattern = Pattern.compile("[0-9]+");
        //如果condition为11位数字
        if(pattern.matcher(condition).matches()&&condition.length()==11){
            criteria.andPhoneEqualTo(condition);
            return employeeMapper.selectByExample(employeeExample);
        //如果condition为纯数字
        }else if (pattern.matcher(condition).matches()){
            criteria.andEmployeeNumberEqualTo(Integer.parseInt(condition));
            return employeeMapper.selectByExample(employeeExample);
        }else{
            criteria.andNameEqualTo(condition);
            List<Employee> list = employeeMapper.selectByExample(employeeExample);
            return list;

        }
    }
}
