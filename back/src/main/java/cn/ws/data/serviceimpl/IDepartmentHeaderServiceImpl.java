package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.DepartmentHeaderMapper;
import cn.ws.data.entity.DepartmentHeader;
import cn.ws.data.service.IDepartmentHeaderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IDepartmentHeaderServiceImpl extends ServiceImpl<DepartmentHeaderMapper, DepartmentHeader> implements IDepartmentHeaderService {

}
