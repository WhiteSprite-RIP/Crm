package cn.ws.data.serviceimpl;

import cn.ws.data.dao.mapper.FileMapper;
import cn.ws.data.entity.File;
import cn.ws.data.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author WhiteSprite
 */
@Service
public class IFileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
