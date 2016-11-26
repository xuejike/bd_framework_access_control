package com.bidanet.bdcms.plugin.bdAc.Interceptor;

import com.bidanet.bdcms.plugin.bdAc.core.AcResource;
import com.bidanet.bdcms.plugin.bdAc.core.AcResourceManage;
import com.bidanet.bdcms.plugin.bdAc.core.AcUserManage;
import com.bidanet.bdcms.plugin.bdAc.exception.NoAuthAcException;
import com.bidanet.bdcms.plugin.bdAc.filter.AcFilter;
import com.bidanet.bdcms.plugin.bdAc.filter.DefaultAcFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 访问控制器
 */
public class AcInterceptor implements HandlerInterceptor {

//    protected Logger logger=Logger.getLogger(AcInterceptor.class);



    protected List<AcFilter> acFilters;


    @Autowired
    protected DefaultAcFilter defaultAcFilter;



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //如果未配置过滤器，则使用默认过滤器
        if (acFilters!=null&&acFilters.size()>0){
            for (AcFilter acFilter : acFilters) {
                if (!acFilter.handle(httpServletRequest, httpServletResponse, o)){
                    throw new NoAuthAcException("未授权");
//                    return false;
                }
            }
        }else{
            if (!defaultAcFilter.handle(httpServletRequest, httpServletResponse, o)){
                throw new NoAuthAcException("未授权");
            }
//            return ;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    public List<AcFilter> getAcFilters() {
        return acFilters;
    }

    public void setAcFilters(List<AcFilter> acFilters) {
        this.acFilters = acFilters;
    }
}
