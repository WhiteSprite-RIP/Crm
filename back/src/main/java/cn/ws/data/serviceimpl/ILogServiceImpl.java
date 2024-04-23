package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.LogMapper;
import cn.ws.data.entity.Log;
import cn.ws.data.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class ILogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
