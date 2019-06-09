package org.nora.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nora.modules.system.dto.PermissionDto;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionUtilTest {

    @Test
    public void listPermission() {
        List<PermissionDto> permissionDtos = PermissionUtil.listPermission();
        System.out.println(permissionDtos);
    }
}