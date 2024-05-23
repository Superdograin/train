package cn.qiluno.train.common.controller;

import cn.qiluno.train.common.exception.BusinessException;
import cn.qiluno.train.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 处理系统异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(Exception e) throws Exception {
        CommonResp<Object> commonResp = new CommonResp<>();
        LOG.error("系统异常", e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统出现异常，请联系管理员");
        return commonResp;
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(BusinessException e) {
        CommonResp<Object> commonResp = new CommonResp<>();
        LOG.error("业务异常: {}", e.getE().getMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getMessage());
        return commonResp;
    }

    /**
     * 处理校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(MethodArgumentNotValidException e) {
        CommonResp<Object> commonResp = new CommonResp<>();
        for (var err : e.getBindingResult().getAllErrors()) {
            LOG.error("检验异常: {}", err.getDefaultMessage());
        }
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }
}
