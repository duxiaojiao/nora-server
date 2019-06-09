package org.nora.modules.system.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuPermissionMapperTest {

    @Autowired
    private SysMenuPermissionMapper permissionMapper;

    @Test
    public void getUserPermissions() {
        List<String> permissions = permissionMapper.getUserPermissions("94394a9ac0cc1a4bf8d9698d76eeca9d");
        System.out.println(permissions);
    }
}