package com.bidanet.bdcms.plugin.bdAc.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuejike on 2016/11/21.
 */
public interface AcFilter {
    boolean handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o);
}
