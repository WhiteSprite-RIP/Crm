package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.DictMapper;
import cn.ws.data.entity.Dict;
import cn.ws.data.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IDictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}
