package org.nora.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nora.common.annotation.PermInfo;
import org.nora.modules.system.dto.PermissionDto;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionUtil {

    private static String  basicPackage = "org.nora.modules.system.controller";

    public static List<PermissionDto> listPermission() {
        ArrayList<PermissionDto> permissionDtos = new ArrayList<>();
        ApplicationContext context = SpringContextUtils.getApplicationContext();
        Map<String, Object> map = context.getBeansWithAnnotation(Controller.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object bean = entry.getValue();
            if (!StringUtils.contains(ClassUtils.getPackageName(bean.getClass()), basicPackage)) {
                continue;
            }

            Class<?> clz = bean.getClass();
            Class controllerClz = clz.getSuperclass();
            RequestMapping clazzRequestMapping = AnnotationUtils.findAnnotation(controllerClz, RequestMapping.class);
            List<Method> methods = MethodUtils.getMethodsListWithAnnotation(controllerClz, RequiresPermissions.class);
            for (Method method : methods) {
                RequiresPermissions requiresPermissions = AnnotationUtils.getAnnotation(method, RequiresPermissions.class);
                PermInfo permInfo = AnnotationUtils.getAnnotation(method, PermInfo.class);
                PostMapping postMapping = AnnotationUtils.getAnnotation(method, PostMapping.class);
                GetMapping getMapping = AnnotationUtils.getAnnotation(method, GetMapping.class);
                PermissionDto permissionDto = new PermissionDto();
                if(requiresPermissions.value().length>0) {
                    permissionDto.setName(permInfo.value());
                    permissionDto.setPermission(requiresPermissions.value()[0]);
                    if (postMapping != null) {
                        permissionDto.setMethod("POST");
                        permissionDto.setMapping(clazzRequestMapping.value()[0] + "/" + postMapping.value()[0]);
                    } else if (getMapping != null) {
                        permissionDto.setMethod("GET");
                        permissionDto.setMapping(clazzRequestMapping.value()[0] + "/" + getMapping.value()[0]);
                    }
                    permissionDtos.add(permissionDto);
                }
            }

        }
        return permissionDtos;
    }

}
