package nuc.jyg.crm.service.lxj;

import nuc.jyg.crm.common.ResponseCodeEnum;
import nuc.jyg.crm.model.Service;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/5 10:00
 * User:Lee
 */
public interface ServiceService {
    ResponseCodeEnum inquireServe();
    ResponseCodeEnum createServing(Service service);
    ResponseCodeEnum handleServe();
    ResponseCodeEnum assignServe();
    ResponseCodeEnum feedBackServe();
    List<Service> filingServe();
}
