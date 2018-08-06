package service;

import dao.ManagerMapper;
import dao.RightMapper;
import entity.Manager;
import entity.ManagerExample;
import entity.Right;
import entity.RightExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jimvio on 2018/7/18.
 */
@Service
public class RightService {
    @Autowired
    RightMapper rightMapper;
    @Autowired
    ManagerMapper managerMapper;

    public List<Right> getAllRights() {
        return rightMapper.selectByExample(null);
    }


    public List<Right> getRightsByCondition(String condition) {
        RightExample rightExample = new RightExample();
        RightExample.Criteria criteria = rightExample.createCriteria();
        Pattern pattern = Pattern.compile("[0-9]+");
        if (pattern.matcher(condition).matches()) {
            criteria.andEmployeeNumberEqualTo(Integer.parseInt(condition));
            List<Right> list = rightMapper.selectByExample(rightExample);
            if (!list.isEmpty()) {
                return list;
            }
        }

        RightExample rightExample1 = new RightExample();
        RightExample.Criteria criteria1 = rightExample1.createCriteria();
        criteria1.andUsernameEqualTo(condition);
        List<Right> list1 = rightMapper.selectByExample(rightExample1);
        return list1;

    }

    public boolean addRight(String userName) {
        RightExample rightExample = new RightExample();
        RightExample.Criteria criteria = rightExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<Right> rights = rightMapper.selectByExample(rightExample);
        if (!rights.isEmpty()) {
            return false;
        } else {
            ManagerExample managerExample = new ManagerExample();
            ManagerExample.Criteria criteria1 = managerExample.createCriteria();
            criteria1.andUsernameEqualTo(userName);
            List<Manager> managers = managerMapper.selectByExample(managerExample);
            if (managers.isEmpty()){
                return false;
            }
            Manager manager = managers.get(0);
            Right right = new Right();
            right.setUsername(userName);
            right.setEmployeeNumber(manager.getEmployeeNumber());
            rightMapper.insert(right);
            return true;
        }
    }

    public boolean deleteRight(String userName) {
        RightExample rightExample = new RightExample();
        RightExample.Criteria criteria = rightExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        if (rightMapper.deleteByExample(rightExample) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
