package nuc.jyg.crm.handler;

import lombok.extern.log4j.Log4j2;
import nuc.jyg.crm.common.ResponseCodeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ji YongGuang.
 * @date 10:17 2018/09/04.
 */
@Log4j2
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e) {
        log.error("{} Exception", httpServletRequest.getRequestURI(), e);
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

        modelAndView.addObject("status", ResponseCodeEnum.ERROR.getCode());
        modelAndView.addObject("msg", "详情查看服务端日志异常信息");
        modelAndView.addObject("data", e.toString());
        return modelAndView;
    }
}
