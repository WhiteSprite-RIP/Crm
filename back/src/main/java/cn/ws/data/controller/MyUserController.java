package cn.ws.data.controller;

import cn.ws.basics.log.LogType;
import cn.ws.basics.log.SystemLog;
import cn.ws.basics.utils.PageUtil;
import cn.ws.basics.utils.ResultUtil;
import cn.ws.basics.baseVo.PageVo;
import cn.ws.basics.baseVo.Result;
import cn.ws.data.entity.User;
import cn.ws.data.service.IUserService;
import cn.ws.data.utils.WsNullUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author WhiteSprite
 */
@Slf4j
@RestController
@Api(tags = "新用户接口")
@RequestMapping("/ws/myUser")
@Transactional
public class MyUserController {

    @Autowired
    private IUserService iUserService;

    @SystemLog(about = "查询用户", type = LogType.DATA_CENTER,doType = "USER-01")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户")
    public Result<IPage<User>> getByPage(@ModelAttribute User user,@ModelAttribute PageVo page){
        QueryWrapper<User> qw = new QueryWrapper<>();
        if(user.getDepartmentId() != null && !WsNullUtils.isNull(user.getDepartmentId())) {
            qw.like("department_id",user.getDepartmentId());
        }
        if(user.getNickname() != null && !WsNullUtils.isNull(user.getNickname())) {
            qw.like("nickname",user.getNickname());
        }
        IPage<User> data = iUserService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<User>>().setData(data);
    }
}
