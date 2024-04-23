package cn.ws.cust.serviceimpl;

import cn.ws.cust.mapper.CustomerMapper;
import cn.ws.cust.entity.Customer;
import cn.ws.cust.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户 服务层接口实现
 * @author WhiteSprite
 */
@Slf4j
@Service
@Transactional
public class ICustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;
}