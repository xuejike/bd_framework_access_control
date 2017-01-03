package com.bidanet.bdcms.plugin.bdAc.filter;


import com.bidanet.bdcms.plugin.bdAc.core.AcResource;
import com.bidanet.bdcms.plugin.bdAc.core.AcResourceManage;
import com.bidanet.bdcms.plugin.bdAc.core.AcUserManage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuejike on 2016/11/21.
 */

@Service
public class DefaultAcFilter implements AcFilter {

    protected Logger logger= Logger.getLogger(DefaultAcFilter.class);

    @Autowired
    AcResourceManage acResourceManage;

    @Autowired
    AcUserManage acUserManage;

    @Override
    public boolean handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        try {
            Map<String, AcResource> resourceMap = acResourceManage.getResourceMap();
            Map<String, Set<String>> roleMap = acResourceManage.getRoleMap();

            //获取请求路径
            String requestURI = httpServletRequest.getRequestURI();
            //获取contextPath
            String contextPath = httpServletRequest.getContextPath();
            //移除contextPath

            String url = requestURI.replaceFirst(contextPath,"");
            url=url.replaceAll("/+","/");
            AcResource acResource = resourceMap.get(url);
            //如果url不存在，则采用默认放过
            if (acResource==null){
                return true;
            }
            String resourceId = acResource.getId();
            //获取当前登录用户角色
            Set<String> set = acUserManage.getCurrentUserRoleKey();
            if (set!=null&&set.size()>0){
                //当前用户登录角色
                for (String roleKey : set) {
                    Set<String> resSet = roleMap.get(roleKey);
                    //检测当前角色 是否有该权限
                    if (resSet.contains(resourceId)){
                        return true;
                    }
                }
            }else{
                //无法获取当前用户登录角色
                return false;
            }

        }catch (Exception e){
            logger.trace("权限异常",e);
        }
        return false;
    }
}
