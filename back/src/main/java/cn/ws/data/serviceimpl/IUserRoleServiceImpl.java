package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.UserRoleMapper;
import cn.ws.data.entity.UserRole;
import cn.ws.data.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
