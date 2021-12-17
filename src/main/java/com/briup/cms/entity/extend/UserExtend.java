package com.briup.cms.entity.extend;

import com.briup.cms.entity.Role;
import com.briup.cms.entity.User;
import lombok.Data;

/**
 * @Auther: vanse
 * @Date: 2021/12/13-12-13-22:17
 * @Description：com.briup.cms.entity.extend
 * @version：1.0
 */
@Data
public class UserExtend extends User {
    private Role role;
}
