package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.DepartmentMapper;
import cn.ws.data.entity.Department;
import cn.ws.data.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IDepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
