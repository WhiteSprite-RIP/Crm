package cn.ws.cust.controller;

import cn.hutool.core.date.DateUtil;
import cn.ws.basics.utils.PageUtil;
import cn.ws.basics.utils.ResultUtil;
import cn.ws.basics.baseVo.PageVo;
import cn.ws.basics.baseVo.Result;
import cn.ws.basics.utils.SecurityUtil;
import cn.ws.cust.entity.Commodity;
import cn.ws.cust.entity.Customer;
import cn.ws.cust.service.ICommodityService;
import cn.ws.cust.service.ICustomerService;
import cn.ws.data.utils.WsNullUtils;
import cn.ws.cust.entity.CustomerOrder;
import cn.ws.cust.service.ICustomerOrderService;
import cn.ws.data.vo.AntvVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author WhiteSprite
 */
@Slf4j
@RestController
@Api(tags = "客户消费管理接口")
@RequestMapping("/ws/customerOrder")
@Transactional
public class CustomerOrderController {

    @Autowired
    private ICustomerOrderService iCustomerOrderService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICommodityService iCommodityService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条客户消费")
    public Result<CustomerOrder> get(@RequestParam String id){
        return new ResultUtil<CustomerOrder>().setData(iCustomerOrderService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部客户消费个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCustomerOrderService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部客户消费")
    public Result<List<CustomerOrder>> getAll(){
        return new ResultUtil<List<CustomerOrder>>().setData(iCustomerOrderService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询客户消费")
    public Result<IPage<CustomerOrder>> getByPage(@ModelAttribute CustomerOrder customerOrder ,@ModelAttribute PageVo page){
        QueryWrapper<CustomerOrder> qw = new QueryWrapper<>();
        if(!WsNullUtils.isNull(customerOrder.getTitle())) {
            qw.like("title",customerOrder.getTitle());
        }
        if(!WsNullUtils.isNull(customerOrder.getUserName())) {
            qw.like("user_name",customerOrder.getUserName());
        }
        IPage<CustomerOrder> data = iCustomerOrderService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<CustomerOrder>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改客户消费")
    public Result<CustomerOrder> saveOrUpdate(CustomerOrder customerOrder){
        if(iCustomerOrderService.saveOrUpdate(customerOrder)){
            return new ResultUtil<CustomerOrder>().setData(customerOrder);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增客户消费")
    public Result<CustomerOrder> insert(CustomerOrder customerOrder){
        Customer customer = iCustomerService.getById(customerOrder.getUserId());
        if(customer == null) {
            return ResultUtil.error("客户不存在");
        }
        customerOrder.setUserName(customer.getTitle());
        Commodity commodity = iCommodityService.getById(customerOrder.getCommodityId());
        if(commodity == null) {
            return ResultUtil.error("商品不存在");
        }
        if(commodity.getNumber() == null) {
            commodity.setNumber(BigDecimal.ZERO);
        }
        if(customerOrder.getNumber().compareTo(commodity.getNumber()) > 0) {
            return ResultUtil.error("库存不足");
        }
        commodity.setNumber(commodity.getNumber().subtract(customerOrder.getNumber()));
        iCommodityService.saveOrUpdate(commodity);
        customerOrder.setTitle(commodity.getTitle());
        customerOrder.setType(commodity.getType());
        customerOrder.setPrice(commodity.getPrice());
        customerOrder.setImage(commodity.getImage());
        customerOrder.setUserId(customer.getId());
        customerOrder.setUserName(customer.getTitle());
        customerOrder.setDate(DateUtil.now());
        iCustomerOrderService.saveOrUpdate(customerOrder);
        return new ResultUtil<CustomerOrder>().setData(customerOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑客户消费")
    public Result<CustomerOrder> update(CustomerOrder customerOrder){
        CustomerOrder old = iCustomerOrderService.getById(customerOrder.getId());
        Customer customer = iCustomerService.getById(customerOrder.getUserId());
        if(customer == null) {
            return ResultUtil.error("客户不存在");
        }
        customerOrder.setUserName(customer.getTitle());
        Commodity commodity = iCommodityService.getById(customerOrder.getCommodityId());
        if(commodity == null) {
            return ResultUtil.error("商品不存在");
        }
        customerOrder.setTitle(commodity.getTitle());
        customerOrder.setType(commodity.getType());
        customerOrder.setPrice(commodity.getPrice());
        customerOrder.setImage(commodity.getImage());
        iCustomerOrderService.saveOrUpdate(customerOrder);
        if(commodity.getNumber() == null) {
            commodity.setNumber(BigDecimal.ZERO);
        }
        commodity.setNumber(commodity.getNumber().add(old.getNumber().subtract(customerOrder.getNumber())));
        iCommodityService.saveOrUpdate(commodity);
        return new ResultUtil<CustomerOrder>().setData(customerOrder);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除客户消费")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            CustomerOrder customerOrder = iCustomerOrderService.getById(id);
            if(customerOrder != null) {
                Commodity commodity = iCommodityService.getById(customerOrder.getCommodityId());
                if(commodity != null) {
                    if(commodity.getNumber() == null) {
                        commodity.setNumber(BigDecimal.ZERO);
                    }
                    commodity.setNumber(commodity.getNumber().add(customerOrder.getNumber()));
                    iCommodityService.saveOrUpdate(commodity);
                }
                iCustomerOrderService.removeById(customerOrder.getId());
            }
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        List<CustomerOrder> teacherList = iCustomerOrderService.list();
        for (CustomerOrder o : teacherList) {
            boolean flag = false;
            for (AntvVo vo : ansList) {
                if(Objects.equals(vo.getTitle(),o.getTitle())) {
                    flag = true;
                    vo.setValue(vo.getValue().add(o.getNumber()));
                    break;
                }
            }
            if(!flag) {
                AntvVo vo = new AntvVo();
                vo.setTitle(o.getTitle());
                vo.setType("商品消费");
                vo.setValue(o.getNumber());
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }
}
