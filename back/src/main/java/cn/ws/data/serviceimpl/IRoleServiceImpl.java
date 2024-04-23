package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.RoleMapper;
import cn.ws.data.entity.Role;
import cn.ws.data.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
