package cn.ws.basics.exception;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @author WhiteSprite
 */
@ApiOperation(value = "自定义异常")
public class WsAuthException extends InternalAuthenticationServiceException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MSG = "系统鉴权失败";

    @ApiModelProperty(value = "异常消息内容")
    private String msg;

    public WsAuthException(String msg){
        super(msg);
        this.msg = msg;
    }

    public WsAuthException(){
        super(DEFAULT_MSG);
        this.msg = DEFAULT_MSG;
    }

    public WsAuthException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
