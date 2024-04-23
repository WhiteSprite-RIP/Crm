package cn.ws.data.vo;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * @author WhiteSprite
 */
@Api(tags = "用户菜单权限VO类")
@Data
public class UserByPermissionVo {
    private String userId;
    private String userName;
    private String roleStr;
    private String code;
    private String mobile;
}
