package com.pixburb.pixburbcommerce.services;

import com.pixburb.pixburbcommerce.data.RoleData;

public interface RoleService {

    boolean createRole(RoleData roleData);

    boolean removeRole(String roleName);

}
