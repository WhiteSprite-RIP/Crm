package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.UserMapper;
import cn.ws.data.entity.User;
import cn.ws.data.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
