package nuc.jyg.crm.service.hikari;

import nuc.jyg.crm.model.Customer;

import java.util.ArrayList;
import java.util.List;

public interface ICustomerService {

    List<Customer> queryList();

    int deleteById(Integer id);

    int insert(Customer customer);

    int update(Customer customer);

    Customer queryById(Integer id);

    ArrayList<Customer> selectByLeval(int leval) ;
}
