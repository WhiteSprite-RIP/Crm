package cn.ws.cust.serviceimpl;

import cn.ws.cust.mapper.CustomerItemMapper;
import cn.ws.cust.entity.CustomerItem;
import cn.ws.cust.service.ICustomerItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户跟踪 服务层接口实现
 * @author WhiteSprite
 */
@Slf4j
@Service
@Transactional
public class ICustomerItemServiceImpl extends ServiceImpl<CustomerItemMapper, CustomerItem> implements ICustomerItemService {

    @Autowired
    private CustomerItemMapper customerItemMapper;
}