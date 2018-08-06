package service;

import dao.DriverMapper;
import entity.Driver;
import entity.DriverExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jimvio on 2018/7/18.
 */
@Service
public class DriverService {
    @Autowired
    DriverMapper driverMapper;

    public List<Driver> getAllDrivers() {
        List<Driver> list = driverMapper.selectByExample(null);
        return list;
    }

    public List<Driver> getDriversByCondition(String condition) {
        DriverExample driverExample = new DriverExample();
        DriverExample.Criteria criteria = driverExample.createCriteria();
        Pattern pattern = Pattern.compile("[0-9]+");
        Pattern pattern1 = Pattern.compile("[0-9a-zA-Z]*");
        if(pattern.matcher(condition).matches()&&condition.length()==11){   //手机号
            criteria.andPhoneEqualTo(condition);
            return driverMapper.selectByExample(driverExample);
        }else if(pattern.matcher(condition).matches()){    //  工号
            criteria.andEmployeeIdEqualTo(Integer.parseInt(condition));
            return driverMapper.selectByExample(driverExample);
        }else if(pattern1.matcher(condition).matches()){     //  用户名
            criteria.andUsernameEqualTo(condition);
            return driverMapper.selectByExample(driverExample);
        }else if(pattern1.matcher(condition.substring(1)).matches()){   //车牌号
            criteria.andCarNumberEqualTo(condition);
            return driverMapper.selectByExample(driverExample);
        }else{  //  姓名
            criteria.andNameEqualTo(condition);
            return driverMapper.selectByExample(driverExample);
        }
    }
}
