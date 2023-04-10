package com.wqb.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wqb.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqb.vo.StudentGradeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    Student queryByUsername(String name);
    IPage selectStudentGrade(IPage<Student> page, @Param(Constants.WRAPPER) Wrapper<StudentGradeVo> ew);

    List<StudentGradeVo> selectLogin(@Param(Constants.WRAPPER) Wrapper ew);

    IPage selectAll(IPage<Student> page);
}
