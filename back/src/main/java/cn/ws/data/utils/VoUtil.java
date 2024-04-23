package cn.ws.data.utils;

import cn.ws.data.entity.Permission;
import cn.ws.data.vo.MenuVo;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author WhiteSprite
 */
@Api(tags = "菜单转换VO类")
public class VoUtil {

    @ApiOperation(value = "菜单转换VO类转换")
    public static MenuVo permissionToMenuVo(Permission permission){
        MenuVo vo = new MenuVo();
        BeanUtil.copyProperties(permission, vo);
        return vo;
    }
}
