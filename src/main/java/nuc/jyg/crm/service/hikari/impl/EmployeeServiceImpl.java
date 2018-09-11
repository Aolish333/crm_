package nuc.jyg.crm.service.hikari.impl;

import nuc.jyg.crm.dao.EmployeeMapper;
import nuc.jyg.crm.model.Employee;
import nuc.jyg.crm.service.hikari.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ji YongGuang.
 * @date 18:37 2018/9/11.
 */
@Service(value = "iEmployeeService")
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getByNameAndPassword(String username, String password) {
        return employeeMapper.selectByNameAndPassowrd(username, password);
    }
}