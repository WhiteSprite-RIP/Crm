package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.PermissionMapper;
import cn.ws.data.entity.Permission;
import cn.ws.data.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
