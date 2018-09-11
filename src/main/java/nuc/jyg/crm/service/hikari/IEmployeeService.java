package nuc.jyg.crm.service.hikari;

import nuc.jyg.crm.model.Employee;

/**
 * @author Ji YongGuang.
 * @date 18:37 2018/9/11.
 */
public interface IEmployeeService {

    Employee getByNameAndPassword(String username, String password);
}
