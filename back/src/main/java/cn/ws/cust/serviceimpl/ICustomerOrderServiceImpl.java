package cn.ws.cust.serviceimpl;

import cn.ws.cust.mapper.CustomerOrderMapper;
import cn.ws.cust.entity.CustomerOrder;
import cn.ws.cust.service.ICustomerOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户消费 服务层接口实现
 * @author WhiteSprite
 */
@Slf4j
@Service
@Transactional
public class ICustomerOrderServiceImpl extends ServiceImpl<CustomerOrderMapper, CustomerOrder> implements ICustomerOrderService {

    @Autowired
    private CustomerOrderMapper customerOrderMapper;
}