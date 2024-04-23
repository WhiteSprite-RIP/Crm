package cn.ws.cust.controller;

import cn.hutool.core.date.DateUtil;
import cn.ws.basics.utils.PageUtil;
import cn.ws.basics.utils.ResultUtil;
import cn.ws.basics.baseVo.PageVo;
import cn.ws.basics.baseVo.Result;
import cn.ws.basics.utils.SecurityUtil;
import cn.ws.cust.entity.Customer;
import cn.ws.cust.service.ICustomerService;
import cn.ws.data.entity.User;
import cn.ws.data.utils.WsNullUtils;
import cn.ws.cust.entity.CustomerItem;
import cn.ws.cust.service.ICustomerItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WhiteSprite
 */
@Slf4j
@RestController
@Api(tags = "客户跟踪管理接口")
@RequestMapping("/ws/customerItem")
@Transactional
public class CustomerItemController {

    @Autowired
    private ICustomerItemService iCustomerItemService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条客户跟踪")
    public Result<CustomerItem> get(@RequestParam String id){
        return new ResultUtil<CustomerItem>().setData(iCustomerItemService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部客户跟踪个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCustomerItemService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部客户跟踪")
    public Result<List<CustomerItem>> getAll(){
        return new ResultUtil<List<CustomerItem>>().setData(iCustomerItemService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询客户跟踪")
    public Result<IPage<CustomerItem>> getByPage(@ModelAttribute CustomerItem customerItem ,@ModelAttribute PageVo page){
        QueryWrapper<CustomerItem> qw = new QueryWrapper<>();
        if(!WsNullUtils.isNull(customerItem.getCustomerName())) {
            qw.like("customer_name",customerItem.getCustomerName());
        }
        if(!WsNullUtils.isNull(customerItem.getUserName())) {
            qw.like("user_name",customerItem.getUserName());
        }
        if(!WsNullUtils.isNull(customerItem.getContent())) {
            qw.like("content",customerItem.getContent());
        }
        IPage<CustomerItem> data = iCustomerItemService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<CustomerItem>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改客户跟踪")
    public Result<CustomerItem> saveOrUpdate(CustomerItem customerItem){
        if(iCustomerItemService.saveOrUpdate(customerItem)){
            return new ResultUtil<CustomerItem>().setData(customerItem);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增客户跟踪")
    public Result<CustomerItem> insert(CustomerItem customerItem){
        Customer customer = iCustomerService.getById(customerItem.getCustomerId());
        if(customer == null) {
            return ResultUtil.error("客户不存在");
        }
        User currUser = securityUtil.getCurrUser();
        customerItem.setCustomerName(customer.getTitle());
        customerItem.setDate(DateUtil.now());
        customerItem.setUserId(currUser.getId());
        customerItem.setUserName(currUser.getNickname());
        iCustomerItemService.saveOrUpdate(customerItem);
        return new ResultUtil<CustomerItem>().setData(customerItem);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑客户跟踪")
    public Result<CustomerItem> update(CustomerItem customerItem){
        Customer customer = iCustomerService.getById(customerItem.getCustomerId());
        if(customer == null) {
            return ResultUtil.error("客户不存在");
        }
        customerItem.setCustomerName(customer.getTitle());
        iCustomerItemService.saveOrUpdate(customerItem);
        return new ResultUtil<CustomerItem>().setData(customerItem);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除客户跟踪")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iCustomerItemService.removeById(id);
        }
        return ResultUtil.success();
    }
}
