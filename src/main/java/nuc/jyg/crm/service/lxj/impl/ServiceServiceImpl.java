package nuc.jyg.crm.service.lxj.impl;

import nuc.jyg.crm.common.ResponseCodeEnum;
import nuc.jyg.crm.dao.ServiceMapper;
import nuc.jyg.crm.model.Service;
import nuc.jyg.crm.service.lxj.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/5 17:40
 * User:Lee
 */
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public ResponseCodeEnum inquireServe() {
        return null;
    }

    @Override
    public ResponseCodeEnum createServing(Service service) {
        serviceMapper.insertSelective(service);
        return ResponseCodeEnum.SUCCESS;
    }

    @Override
    public ResponseCodeEnum handleServe() {
        return null;
    }

    @Override
    public ResponseCodeEnum assignServe() {
        return null;
    }

    @Override
    public ResponseCodeEnum feedBackServe() {
        return null;
    }

    @Override
    public List <Service> filingServe() {
        return null;
    }
}
