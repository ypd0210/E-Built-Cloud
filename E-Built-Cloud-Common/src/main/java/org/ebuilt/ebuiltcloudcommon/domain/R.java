package org.ebuilt.ebuiltcloudcommon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.ebuilt.ebuiltcloudcommon.exception.exceptionInfo.CommonException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class R<T> {
    private int code;
    private String msg;
    private T data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public static R<Void> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(200, "OK", data);
    }

    public static <T> R<T> error(String msg) {
        return new R<>(500, msg + "请联系开发团队:superoa_dev_support_team@foxmail.com", null);
    }

    public static <T> R<T> error(int code, String msg) {
        return new R<>(code, msg + "请联系开发团队:superoa_dev_support_team@foxmail.com", null);
    }

    public static <T> R<T> error(CommonException e) {
        return new R<>(500, e.getMessage(), null);
    }

    public R() {
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        // 获取当前的 LocalDateTime
        this.time = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public boolean success() {
        return code == 200;
    }
}