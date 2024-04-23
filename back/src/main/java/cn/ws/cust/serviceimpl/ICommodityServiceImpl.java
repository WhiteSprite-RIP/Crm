package cn.ws.cust.serviceimpl;

import cn.ws.cust.mapper.CommodityMapper;
import cn.ws.cust.entity.Commodity;
import cn.ws.cust.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品 服务层接口实现
 * @author WhiteSprite
 */
@Slf4j
@Service
@Transactional
public class ICommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
}