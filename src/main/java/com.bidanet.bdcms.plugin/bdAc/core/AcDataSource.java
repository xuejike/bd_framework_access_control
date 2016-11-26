package com.bidanet.bdcms.plugin.bdAc.core;

import java.util.List;
import java.util.Set;

/**
 * Created by xuejike on 2016/11/21.
 */
public interface AcDataSource {

    Set<AcResource> getAllResource();
    Set<AcRole> getAllRole();
}
