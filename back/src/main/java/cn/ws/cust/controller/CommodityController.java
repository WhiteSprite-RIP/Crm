package cn.ws.cust.controller;

import cn.ws.basics.utils.PageUtil;
import cn.ws.basics.utils.ResultUtil;
import cn.ws.basics.baseVo.PageVo;
import cn.ws.basics.baseVo.Result;
import cn.ws.data.utils.WsNullUtils;
import cn.ws.cust.entity.Commodity;
import cn.ws.cust.service.ICommodityService;
import cn.ws.data.vo.AntvVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author WhiteSprite
 */
@Slf4j
@RestController
@Api(tags = "商品管理接口")
@RequestMapping("/ws/commodity")
@Transactional
public class CommodityController {

    @Autowired
    private ICommodityService iCommodityService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条商品")
    public Result<Commodity> get(@RequestParam String id){
        return new ResultUtil<Commodity>().setData(iCommodityService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部商品个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCommodityService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部商品")
    public Result<List<Commodity>> getAll(){
        return new ResultUtil<List<Commodity>>().setData(iCommodityService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    public Result<IPage<Commodity>> getByPage(@ModelAttribute Commodity commodity ,@ModelAttribute PageVo page){
        QueryWrapper<Commodity> qw = new QueryWrapper<>();
        if(!WsNullUtils.isNull(commodity.getTitle())) {
            qw.like("title",commodity.getTitle());
        }
        if(!WsNullUtils.isNull(commodity.getType())) {
            qw.eq("type",commodity.getType());
        }
        IPage<Commodity> data = iCommodityService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Commodity>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改商品")
    public Result<Commodity> saveOrUpdate(Commodity commodity){
        if(iCommodityService.saveOrUpdate(commodity)){
            return new ResultUtil<Commodity>().setData(commodity);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增商品")
    public Result<Commodity> insert(Commodity commodity){
        iCommodityService.saveOrUpdate(commodity);
        return new ResultUtil<Commodity>().setData(commodity);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑商品")
    public Result<Commodity> update(Commodity commodity){
        iCommodityService.saveOrUpdate(commodity);
        return new ResultUtil<Commodity>().setData(commodity);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除商品")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iCommodityService.removeById(id);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        List<Commodity> teacherList = iCommodityService.list();
        for (Commodity o : teacherList) {
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
                vo.setType("商品库存");
                vo.setValue(o.getNumber());
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }
}
