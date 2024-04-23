package cn.ws.cust.controller;

import cn.ws.basics.utils.PageUtil;
import cn.ws.basics.utils.ResultUtil;
import cn.ws.basics.baseVo.PageVo;
import cn.ws.basics.baseVo.Result;
import cn.ws.data.utils.WsNullUtils;
import cn.ws.cust.entity.Customer;
import cn.ws.cust.service.ICustomerService;
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
@Api(tags = "客户管理接口")
@RequestMapping("/ws/customer")
@Transactional
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条客户")
    public Result<Customer> get(@RequestParam String id){
        return new ResultUtil<Customer>().setData(iCustomerService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部客户个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCustomerService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部客户")
    public Result<List<Customer>> getAll(){
        return new ResultUtil<List<Customer>>().setData(iCustomerService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询客户")
    public Result<IPage<Customer>> getByPage(@ModelAttribute Customer customer ,@ModelAttribute PageVo page){
        QueryWrapper<Customer> qw = new QueryWrapper<>();
        if(!WsNullUtils.isNull(customer.getType())) {
            qw.eq("type",customer.getType());
        }
        if(!WsNullUtils.isNull(customer.getTitle())) {
            qw.like("title",customer.getTitle());
        }
        IPage<Customer> data = iCustomerService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Customer>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改客户")
    public Result<Customer> saveOrUpdate(Customer customer){
        if(iCustomerService.saveOrUpdate(customer)){
            return new ResultUtil<Customer>().setData(customer);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增客户")
    public Result<Customer> insert(Customer customer){
        iCustomerService.saveOrUpdate(customer);
        return new ResultUtil<Customer>().setData(customer);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑客户")
    public Result<Customer> update(Customer customer){
        iCustomerService.saveOrUpdate(customer);
        return new ResultUtil<Customer>().setData(customer);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除客户")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iCustomerService.removeById(id);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        List<Customer> teacherList = iCustomerService.list();
        for (Customer o : teacherList) {
            boolean flag = false;
            for (AntvVo vo : ansList) {
                if(Objects.equals(vo.getTitle(),o.getSex())) {
                    flag = true;
                    vo.setValue(vo.getValue().add(BigDecimal.ONE));
                    break;
                }
            }
            if(!flag) {
                AntvVo vo = new AntvVo();
                vo.setTitle(o.getSex());
                vo.setType("客户性别");
                vo.setValue(BigDecimal.ONE);
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }
}
