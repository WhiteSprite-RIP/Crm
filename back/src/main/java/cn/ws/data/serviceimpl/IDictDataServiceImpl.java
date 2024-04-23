package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.DictDataMapper;
import cn.ws.data.entity.DictData;
import cn.ws.data.service.IDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IDictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {

}
