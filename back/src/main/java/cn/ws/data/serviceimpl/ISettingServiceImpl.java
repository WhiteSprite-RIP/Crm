package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.SettingMapper;
import cn.ws.data.entity.Setting;
import cn.ws.data.service.ISettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class ISettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {

}
