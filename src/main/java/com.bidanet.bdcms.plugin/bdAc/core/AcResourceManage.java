package com.bidanet.bdcms.plugin.bdAc.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuejike on 2016/11/19.
 */
@Service
public class AcResourceManage {
    private Map<String,AcResource> resourceMap;
    private Map<String,Set<String>> roleMap;


    @Autowired
    AcDataSource acDataSource;




    @PostConstruct
    private void init(){

        loadAllResource();
        loadAllRole();
    }

    public void refreshResource(){
        loadAllResource();
        loadAllRole();
    }

    /**
     * 加载所有资源
     */
    void loadAllResource(){
        Set<AcResource> allResource = acDataSource.getAllResource();
        resourceMap=new HashMap<>();
        for (AcResource acResource : allResource) {
            resourceMap.put(acResource.getUrl(),acResource);
        }
    }

    /**
     * 加载所有角色
     */
    void loadAllRole(){
        roleMap=new HashMap<>();
        Set<AcRole> allRole = acDataSource.getAllRole();
        for (AcRole acRole : allRole) {
            roleMap.put(acRole.getRoleKey(),acRole.getRoleResourceIds());
        }

    }

    public Map<String, AcResource> getResourceMap() {
        return resourceMap;
    }

    public Map<String, Set<String>> getRoleMap() {
        return roleMap;
    }


}
