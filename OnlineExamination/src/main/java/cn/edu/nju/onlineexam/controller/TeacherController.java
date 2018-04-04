package cn.edu.nju.onlineexam.controller;

import cn.edu.nju.onlineexam.DTO.ResultDTO;
import cn.edu.nju.onlineexam.Exception.LoginInvalidException;
import cn.edu.nju.onlineexam.entity.Student;
import cn.edu.nju.onlineexam.entity.Teacher;
import cn.edu.nju.onlineexam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 老师注册
     * @param account
     * @param password
     * @param courses
     * @param session
     * @return ResultDTO<Teacher>
     */
    @PostMapping(value = "/register")
    public ResultDTO<Teacher> createUser(@RequestParam("account") String account,
                                         @RequestParam("password") String password,
                                         @RequestParam("courses") String courses,
                                         HttpSession session){
        ResultDTO<Teacher> result = new ResultDTO<>();
        try {
            Teacher tmpTeacher = new Teacher();
            tmpTeacher.setAccount(account);
            tmpTeacher.setPassword(password);
            tmpTeacher.setCourses(courses);
            Teacher teacher = teacherService.register(tmpTeacher);
            session.setAttribute("teacherId", teacher.getId());
            result.setResult(true);
            result.setObject(teacher);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }
    /**
     * 老师注销登录
     * @return ResultDTO<Teacher>
     */
    @PostMapping(value="/logout")
    public ResultDTO<Teacher> userLogout(HttpSession session){

        ResultDTO<Teacher> result = new ResultDTO<>();

        try{
            result.setResult(true);
            session.invalidate();
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }
    /**
     * 注销一个教师账户
     * @param id
     * @return ResultDTO<Teacher>
     */
    @DeleteMapping(value = "/delete")
    public ResultDTO<Teacher> deleteUser(@RequestParam("id") long id){
        ResultDTO<Teacher> result = new ResultDTO<>();
        try{
            teacherService.delete(id);
            result.setResult(true);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 教师登录
     * @param account // 规定一下，这边的email是account
     * @param passWord
     * @return boolean
     */
    @PostMapping(value="/login")
    public long userLogin(@RequestParam("email") String account,
                                        @RequestParam("passWord") String passWord,
                                        HttpSession session){

        Teacher teacher = null;
        try {
            teacher = teacherService.login(account,passWord);
        } catch (LoginInvalidException e) {
            e.printStackTrace();
        }
        if(teacher==null){
            return -1;
        }
        session.setAttribute("teacherId",teacher.getId());
        return teacher.getId();
    }

    /**
     * 通过教师id查找某个教师
     * @param id
     * @return  ResultDTO<Teacher>
     */
    @GetMapping(value = "/find/id")
    public ResultDTO<Teacher> getUser(@RequestParam long id){
        ResultDTO<Teacher> result = new ResultDTO<>();
        try{
            Teacher teacher = teacherService.findTeacherById(id);
            if(teacher == null){
                result.setResult(false);
                result.setError("user is not exist");
                return result;
            }
            result.setResult(true);
            result.setObject(teacher);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 通过教师工号查找某个教师
     * @param account
     * @return ResultDTO<Teacher>
     */
    @GetMapping(value = "/find/account")
    public ResultDTO<Teacher> getUser(@RequestParam String account){
        ResultDTO<Teacher> result  = new ResultDTO<>();
        try{
            Teacher teacher = teacherService.findTeacherByAccount(account);
            if(teacher == null){
                result.setResult(false);
                result.setError("user is not exist");
                return result;
            }
            result.setResult(true);
            result.setObject(teacher);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 更新教师信息
     * @param id
     * @param account
     * @param password
     * @param courses
     * @return ResultDTO<Teacher>
     */
    @PutMapping(value = "/update")
    public ResultDTO<Teacher> updateUser(@RequestParam long id,
                                         @RequestParam String account,
                                         @RequestParam String password,
                                         @RequestParam String courses){

        ResultDTO<Teacher> result  = new ResultDTO<>();
        try{
            Teacher new_teacher = teacherService.findTeacherById(id);
            if(new_teacher == null){
                result.setResult(false);
                result.setError("user is not exist");
                return result;
            }

            new_teacher.setAccount(account);
            new_teacher.setPassword(password);
            new_teacher.setCourses(courses);
            teacherService.update(new_teacher);
            result.setResult(true);
            result.setObject(new_teacher);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }
}
