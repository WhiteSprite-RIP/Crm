package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.RolePermissionMapper;
import cn.ws.data.entity.RolePermission;
import cn.ws.data.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IRolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
