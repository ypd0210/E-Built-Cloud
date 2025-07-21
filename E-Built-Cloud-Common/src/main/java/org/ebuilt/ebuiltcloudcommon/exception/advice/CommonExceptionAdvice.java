package org.ebuilt.ebuiltcloudcommon.exception.advice;


import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.ebuilt.ebuiltcloudcommon.domain.R;
import org.ebuilt.ebuiltcloudcommon.exception.exceptionInfo.BadRequestException;
import org.ebuilt.ebuiltcloudcommon.exception.exceptionInfo.CommonException;
import org.ebuilt.ebuiltcloudcommon.exception.exceptionInfo.CustomFeignException;
import org.ebuilt.ebuiltcloudcommon.exception.exceptionInfo.DbException;
import org.ebuilt.ebuiltcloudcommon.utils.WebUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

//import javax.mail.MessagingException;
import java.net.BindException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    @ExceptionHandler(DbException.class)
    public Object handleDbException(DbException e) {
        log.error("mysql数据库操作异常 -> ", e);
        return processResponse(e);
    }

    @ExceptionHandler(CommonException.class)
    public Object handleBadRequestException(CommonException e) {
        //log.error("自定义异常 -> {} , 异常原因：{}  ", e.getClass().getName(), e.getMessage());
        //log.debug("", e);
        return processResponse(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("|"));
        log.error("请求参数校验异常 -> {}", msg);
        log.debug("", e);
        return processResponse(new BadRequestException(msg));
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        log.error("请求参数绑定异常 ->BindException， {}", e.getMessage());
        log.debug("", e);
        return processResponse(new BadRequestException("请求参数格式错误"));
    }

    @ExceptionHandler(NestedServletException.class)
    public Object handleNestedServletException(NestedServletException e) {
        log.error("参数异常 -> NestedServletException，{}", e.getMessage());
        log.debug("", e);
        return processResponse(new BadRequestException("请求参数处理异常"));
    }

    @ExceptionHandler(Exception.class)
    public Object handleRuntimeException(Exception e) {
        log.error("其他异常 uri : {} -> ", WebUtils.getRequest().getRequestURI(), e);
        return processResponse(new CommonException("服务器内部异常", 500));
    }

    private ResponseEntity<R<Void>> processResponse(CommonException e) {
        return ResponseEntity.status(e.getCode()).body(R.error(e));
    }

    @ExceptionHandler(CustomFeignException.class)
    public Object handleFeignException(CustomFeignException e) {
        log.error("openFeign请求异常{}", e.getMessage());
        return processResponse(new CommonException("openFeign请求异常", e.getCause(), e.status()));
    }

/*@ExceptionHandler(MessagingException.class)
    public Object handleEmailException(MessagingException e) {
        log.error("邮件发送失败{}", e.getMessage());
        return processResponse(new CommonException("邮件发送失败", e.getCause(), 500));
    }

    @ExceptionHandler(ArkHttpException.class)
    public Object handleArkHttpException(ArkHttpException e) {
        // 记录异常信息
        log.error("调用火山引擎服务出错:{}", e.getMessage());
        // 返回错误响应
        return processResponse(new CommonException("调用火山引擎服务出错", e.getCause(), e.statusCode));
    }*/


}
