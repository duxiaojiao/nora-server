package org.nora.modules.execption;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.StringUtils;
import org.nora.common.responses.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //不满足@RequiresGuest注解时抛出的异常信息
    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";


    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseType<String> handleShiroException(ShiroException e) {
        ResponseType<String> response = new ResponseType<>();
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}",eName);
        response.failure("鉴权或授权过程出错");
        return response;
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public ResponseType<String> page401(UnauthenticatedException e) {
        ResponseType<String> response = new ResponseType<>();
        String eMsg = e.getMessage();
        if (StringUtils.startsWithIgnoreCase(eMsg,GUEST_ONLY)){
            response.failure("只允许游客访问，若您已登录，请先退出登录");
        }else{
            response.failure("用户未登录");
        }

        return response;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseType<String> page403() {
        ResponseType<String> response = new ResponseType<>();
        response.failure("用户没有访问权限");
        return response;
    }


}
