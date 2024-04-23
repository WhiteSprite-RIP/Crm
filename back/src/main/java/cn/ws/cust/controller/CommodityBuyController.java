package cn.ws.cust.controller;

import cn.hutool.core.date.DateUtil;
import cn.ws.basics.utils.PageUtil;
import cn.ws.basics.utils.ResultUtil;
import cn.ws.basics.baseVo.PageVo;
import cn.ws.basics.baseVo.Result;
import cn.ws.basics.utils.SecurityUtil;
import cn.ws.cust.entity.Commodity;
import cn.ws.cust.service.ICommodityService;
import cn.ws.data.entity.User;
import cn.ws.data.utils.WsNullUtils;
import cn.ws.cust.entity.CommodityBuy;
import cn.ws.cust.service.ICommodityBuyService;
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
@Api(tags = "商品购买管理接口")
@RequestMapping("/ws/commodityBuy")
@Transactional
public class CommodityBuyController {

    @Autowired
    private ICommodityBuyService iCommodityBuyService;

    @Autowired
    private ICommodityService iCommodityService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条商品购买")
    public Result<CommodityBuy> get(@RequestParam String id){
        return new ResultUtil<CommodityBuy>().setData(iCommodityBuyService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部商品购买个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCommodityBuyService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部商品购买")
    public Result<List<CommodityBuy>> getAll(){
        return new ResultUtil<List<CommodityBuy>>().setData(iCommodityBuyService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品购买")
    public Result<IPage<CommodityBuy>> getByPage(@ModelAttribute CommodityBuy commodityBuy ,@ModelAttribute PageVo page){
        QueryWrapper<CommodityBuy> qw = new QueryWrapper<>();
        if(!WsNullUtils.isNull(commodityBuy.getTitle())) {
            qw.like("title",commodityBuy.getTitle());
        }
        if(!WsNullUtils.isNull(commodityBuy.getUserName())) {
            qw.like("user_name",commodityBuy.getUserName());
        }
        IPage<CommodityBuy> data = iCommodityBuyService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<CommodityBuy>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改商品购买")
    public Result<CommodityBuy> saveOrUpdate(CommodityBuy commodityBuy){
        if(iCommodityBuyService.saveOrUpdate(commodityBuy)){
            return new ResultUtil<CommodityBuy>().setData(commodityBuy);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增商品购买")
    public Result<CommodityBuy> insert(CommodityBuy commodityBuy){
        Commodity commodity = iCommodityService.getById(commodityBuy.getCommodityId());
        if(commodity == null) {
            return ResultUtil.error("商品不存在");
        }
        User currUser = securityUtil.getCurrUser();
        commodityBuy.setTitle(commodity.getTitle());
        commodityBuy.setType(commodity.getType());
        commodityBuy.setPrice(commodity.getPrice());
        commodityBuy.setImage(commodity.getImage());
        commodityBuy.setUserId(currUser.getId());
        commodityBuy.setUserName(currUser.getNickname());
        commodityBuy.setDate(DateUtil.now());
        iCommodityBuyService.saveOrUpdate(commodityBuy);
        if(commodity.getNumber() == null) {
            commodity.setNumber(BigDecimal.ZERO);
        }
        commodity.setNumber(commodity.getNumber().add(commodityBuy.getNumber()));
        iCommodityService.saveOrUpdate(commodity);
        return new ResultUtil<CommodityBuy>().setData(commodityBuy);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑商品购买")
    public Result<CommodityBuy> update(CommodityBuy commodityBuy){
        iCommodityBuyService.saveOrUpdate(commodityBuy);
        return new ResultUtil<CommodityBuy>().setData(commodityBuy);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除商品购买")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            CommodityBuy commodityBuy = iCommodityBuyService.getById(id);
            if(commodityBuy != null) {
                Commodity commodity = iCommodityService.getById(commodityBuy.getCommodityId());
                if(commodity != null) {
                    commodity.setNumber(commodity.getNumber().subtract(commodityBuy.getNumber()));
                    iCommodityService.saveOrUpdate(commodity);
                }
                iCommodityBuyService.removeById(commodityBuy);
            }
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        List<CommodityBuy> teacherList = iCommodityBuyService.list();
        for (CommodityBuy o : teacherList) {
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
                vo.setType("商品进货");
                vo.setValue(o.getNumber());
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }
}
