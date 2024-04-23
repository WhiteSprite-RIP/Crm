package cn.ws.test.serviceimpl;

import cn.ws.test.mapper.TeacherMapper;
import cn.ws.test.entity.Teacher;
import cn.ws.test.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author WhiteSprite
 */
@Slf4j
@Service
@Transactional
public class ITeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
}