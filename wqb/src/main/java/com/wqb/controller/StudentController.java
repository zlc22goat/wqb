package com.wqb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.Result;
import com.wqb.common.QueryPageParam;
import com.wqb.entity.Exam;
import com.wqb.entity.Menu;
import com.wqb.entity.Student;
import com.wqb.service.ExamService;
import com.wqb.service.MenuService;
import com.wqb.service.StudentService;
import com.wqb.vo.StudentGradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public List<Student> list() {
        return studentService.list();
    }

    @PostMapping("/selectAll")
    public Result selectAll(@RequestBody QueryPageParam query) {

        Page<Student> studentPage = new Page<>();
        studentPage.setCurrent(query.getPageNum());
        studentPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getRoleid, 1);
//        studentPage.setRecords(studentService.selectAll());

//        IPage result = studentService.selectStudentGrade(studentPage, lambdaQueryWrapper);
        IPage result = studentService.selectAll(studentPage);
//        result.setRecords(studentService.selectAll());
        return Result.suc(result.getRecords());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Student student) {
        Timestamp createTime = new Timestamp(new Date().getTime());

        student.setCreateTime(createTime);
        student.setRoleid(1);

        return studentService.save(student) ? Result.suc() : Result.fail();
    }

    @PostMapping("/saveTea")
    public Result saveTea(@RequestBody Student student) {
        Timestamp createTime = new Timestamp(new Date().getTime());

        student.setCreateTime(createTime);
        student.setRoleid(2);

        return studentService.save(student) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        return studentService.updateById(student) ? Result.suc() : Result.fail();
    }

    @PostMapping("/register")
    public Result register(@RequestBody Student person){
        String studentNo = person.getSNo();
        String password = person.getSPassword();

        //1.判断用户名、密码是否为空
        if(studentNo != null && password != null){
            Student student =  studentService.queryByUsername(studentNo);
            //2.判断是否有重复用户名
            if(student!=null){
                return Result.fail();
            }else {
                person.setRoleid(1);
                person.setCreateTime(new Date());
                boolean count = studentService.save(person);
                if(count){
                    //3.没有重复用户名，注册成功
                    return Result.suc(person);
                }else {
                    return Result.fail();
                }
            }
        }else{
            return Result.fail();
        }
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return studentService.removeById(id) ? Result.suc() : Result.fail();
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Student student) {
        return studentService.updateById(student);
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = studentService.lambdaQuery().eq(Student::getSNo,no).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return studentService.removeById(id);
    }

    @PostMapping("/listP")
    public Result listP(@RequestBody Student student) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(student.getSName())) {
            lambdaQueryWrapper.like(Student::getSName, student.getSName());// 模糊查询
        }
        return Result.suc(studentService.list(lambdaQueryWrapper));
    }

//    @PostMapping("/listPage")
//    public List<Student> listPage(@RequestBody QueryPageParam query) {
////        System.out.println(query);
//
//        // 查找定义好的page的信息
////        System.out.println("num = " + query.getPageNum());
////        System.out.println("size = " + query.getPageSize());
//
//        HashMap param = query.getParam();
//        String name = (String)param.get("sname");
////        System.out.println("name = " + (String)param.get("name"));
////        System.out.println("no = " + (String)param.get("no"));
//        Page<Student> studentPage = new Page<>();
//        studentPage.setCurrent(query.getPageNum());
//        studentPage.setSize(query.getPageSize());
//
//        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.like(Student::getSName, name);
//
//        IPage result = studentService.page(studentPage, lambdaQueryWrapper);
//
//        System.out.println("total = " + result.getTotal());
//
//        return result.getRecords();
//    }

    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getSNo, student.getSNo())
                .eq(Student::getSPassword, student.getSPassword());
        List<StudentGradeVo> result = studentService.selectLogin(lambdaQueryWrapper);
        // 登录时就获取用户的权限，限定以后的访问界面
        if (result.size() > 0) {
            StudentGradeVo studentGradeVo = result.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuright, studentGradeVo.getRoleid()).list();
            HashMap res = new HashMap();
            res.put("student", studentGradeVo);
            res.put("menu", menuList);
            return Result.suc(res);
        }
        return Result.fail();
    }

    @PostMapping("/selectByGrade")
    public Result selectByGrade(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String gradeid = (String)param.get("gradeid");

        Page<Student> studentPage = new Page<>();
        studentPage.setCurrent(query.getPageNum());
        studentPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getGradeid, gradeid);

        IPage result = studentService.selectStudentGrade(studentPage, lambdaQueryWrapper);

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

    /**
     * 姓名模糊查询分页
     * @param query
     * @return
     */
    @PostMapping("/selectByName")
    public Result selectByName(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("sname");
        String gender = (String) param.get("gender");
        Integer role = (Integer) param.get("roleid");

        Page<Student> studentPage = new Page<>();
        studentPage.setCurrent(query.getPageNum());
        studentPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getRoleid, role).like(Student::getSName, name);
        if (!gender.equals("")) {
            lambdaQueryWrapper.eq(Student::getGender, gender);
        }
        if (param.get("squadId") != "") {
            Integer squadId = (Integer) param.get("squadId");
            lambdaQueryWrapper.eq(Student::getSquadId, squadId);
        }

        IPage result = studentService.selectStudentGrade(studentPage, lambdaQueryWrapper);

//        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

    /**
     * 用户名模糊查询分页
     * @param query
     * @return
     */
    @PostMapping("/selectByNo")
    public Result selectByNo(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String sno = (String)param.get("sno");

        Page<Student> studentPage = new Page<>();
        studentPage.setCurrent(query.getPageNum());
        studentPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Student::getSNo, sno);

        IPage result = studentService.selectStudentGrade(studentPage, lambdaQueryWrapper);

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

    @GetMapping("/getGradeName")
    public Result getGradeName() {
        return Result.suc(studentService.getGradeName());
    }

    @GetMapping("/getAllMark")
    public Result getAllMark() {
        return Result.suc(examService.getAllMark());
    }

    @GetMapping("/getNewStu")
    public Result getNewStu() {
        return Result.suc(studentService.getNewStu());
    }
}

