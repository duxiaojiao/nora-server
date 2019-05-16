package org.nora.modules.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCRUD {

    @Autowired
    private SysUserMapper mapper;

    @Test
    public void addUser() {
        SysUser user = new SysUser();
        user.setGuid("1111111111");
        user.setEmpCode("S66156");
        user.setEmpName("赵四");
        mapper.insert(user);

    }
}