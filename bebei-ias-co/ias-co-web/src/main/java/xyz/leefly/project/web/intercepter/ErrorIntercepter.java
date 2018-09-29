package xyz.leefly.project.web.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.leefly.project.web.model.RespData;

@ControllerAdvice
public class ErrorIntercepter {

    private static Logger logger = LoggerFactory.getLogger(ErrorIntercepter.class);

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public RespData myErrorHandler(Throwable ex) {
        logger.error("web has an error", ex);
        RespData resp = new RespData();
        resp.setCode(1);
        resp.setSuccess(false);
        resp.setMsg(ex.toString());
        return resp;
    }

}
