package com.wqb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wqb.vo.StudentGradeVo;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@Service
public interface StudentService extends IService<Student> {
    Student queryByUsername(String name);

    IPage selectStudentGrade(IPage<Student> page, Wrapper wrapper);

    List<StudentGradeVo> selectLogin(Wrapper wrapper);

    IPage selectAll(IPage<Student> studentPage);
}
