package cn.ws.cust.serviceimpl;

import cn.ws.cust.mapper.CommodityBuyMapper;
import cn.ws.cust.entity.CommodityBuy;
import cn.ws.cust.service.ICommodityBuyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品购买 服务层接口实现
 * @author WhiteSprite
 */
@Slf4j
@Service
@Transactional
public class ICommodityBuyServiceImpl extends ServiceImpl<CommodityBuyMapper, CommodityBuy> implements ICommodityBuyService {

    @Autowired
    private CommodityBuyMapper commodityBuyMapper;
}