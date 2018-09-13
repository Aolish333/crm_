package nuc.jyg.crm.service.hikari.impl;

import nuc.jyg.crm.dao.CustomerMapper;
import nuc.jyg.crm.model.Customer;
import nuc.jyg.crm.service.hikari.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:实现类
 *
 * @author 闫文强
 * @create 2018-09-04 11:21
 */
@Service(value = "iCustomerService")
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> queryList() {
        List<Customer> cusList = customerMapper.queryList();
        return cusList;
    }

    @Override
    public int deleteById(Integer id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Customer customer) {
        return customerMapper.insertSelective(customer);
    }

    @Override
    public int update(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public Customer queryById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public ArrayList<Customer> selectByLeval(int leval) {
        return customerMapper.selectByLeval(leval);
    }
}