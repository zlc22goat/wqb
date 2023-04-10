package com.wqb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wqb.entity.Student;
import com.wqb.mapper.StudentMapper;
import com.wqb.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wqb.vo.StudentGradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public IPage selectAll(IPage<Student> page) {
        return studentMapper.selectAll(page);
    }

    @Override
    public IPage selectStudentGrade(IPage<Student> page, Wrapper wrapper) {
        return studentMapper.selectStudentGrade(page, wrapper);
    }

    @Override
    public Student queryByUsername(String name) {
        return studentMapper.queryByUsername(name);
    }

    @Override
    public List<StudentGradeVo> selectLogin(Wrapper wrapper) {
        return studentMapper.selectLogin(wrapper);
    }
}
