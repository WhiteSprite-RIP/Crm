package cn.ws.cust.controller;

import cn.ws.basics.baseVo.Result;
import cn.ws.basics.utils.ResultUtil;
import cn.ws.data.entity.User;
import cn.ws.data.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WhiteSprite
 */
@Slf4j
@RestController
@Api(tags = "超级用户接口")
@RequestMapping("/ws/superUser")
@Transactional
public class SuperUserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部用户")
    public Result<List<User>> getUserList(){
        return new ResultUtil<List<User>>().setData(iUserService.list());
    }
}
