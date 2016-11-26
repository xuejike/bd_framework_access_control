package com.bidanet.bdcms.plugin.bdAc.core;

import java.util.Set;

/**
 * 用户管理
 */
public interface AcUserManage {
    /**
     * 获取当前用户的角色,未登录用户 anon
     * @return
     */
    Set<String> getCurrentUserRoleKey();

}
