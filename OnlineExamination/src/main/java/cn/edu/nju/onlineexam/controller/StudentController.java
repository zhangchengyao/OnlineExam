package cn.edu.nju.onlineexam.controller;

import cn.edu.nju.onlineexam.DTO.ResultDTO;
import cn.edu.nju.onlineexam.Exception.LoginInvalidException;
import cn.edu.nju.onlineexam.entity.Student;
import cn.edu.nju.onlineexam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by user on 2017/11/20.
 *
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     *  学生注册
     *  @param email
     *  @param password
     *  @param name
     *  @param stNo
     *  @param stGrade
     *  @param stClass
     *  @param session
     *  @return ResultDTO<Student>
    */
    @PostMapping(value="/register")
    public ResultDTO<Student> createUser(@RequestParam String email,
                                         @RequestParam String password,
                                         @RequestParam String name,
                                         @RequestParam String stNo,
                                         @RequestParam int stGrade,
                                         @RequestParam int stClass,
                                         HttpSession session){
        ResultDTO<Student> result = new ResultDTO<>();

        try {
            Student tmpStudent = new Student();
            tmpStudent.setEmail(email);
            tmpStudent.setPassword(password);
            tmpStudent.setName(name);
            tmpStudent.setStudentNo(stNo);
            tmpStudent.setGrade(stGrade);
            tmpStudent.setStudentClass(stClass);
            Student student = studentService.register(tmpStudent);
            session.setAttribute("StudentId", student.getId());//如果注册完直接跳转到已登录状态就加这句，如果注册完还需重新登录就删除这句话
            result.setResult(true);
            result.setObject(student);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 学生登录
     * @param email // 规定一下，用户登录一律用邮箱
     * @param passWord
     * @return long
     */
    @PostMapping(value="/login")
    public long userLogin(@RequestParam("email") String email,
                                        @RequestParam("passWord") String passWord,
                                        HttpSession session){

        Student student = null;
        try {
            student = studentService.login(email,passWord);
        } catch (LoginInvalidException e) {
            e.printStackTrace();
        }
        if(student==null){
            return -1;
        }
        session.setAttribute("StudentId",student.getId());
        return student.getId();
    }

    /**
     * 学生注销登录
     * @return ResultDTO<Student>
     */
    @PostMapping(value="/logout")
    public ResultDTO<Student> userLogout(HttpSession session){

        ResultDTO<Student> result  = new ResultDTO<>();

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
     * 注销一个学生账户,先理解为删除学生
     * @param id
     * @return ResultDTO<Student>
    */
    @DeleteMapping(value="/delete")
    public ResultDTO<Student> deleteUser(@RequestParam("id") long id){

        ResultDTO<Student> result  = new ResultDTO<>();

        try{
            studentService.delete(id);
            result.setResult(true);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 通过学生id查找某个学生
     * @param id
     * @return ResultDTO<Student>
    */
    @GetMapping(value="/find/id")
    public ResultDTO<Student> getUser(@RequestParam("id") long id){

        ResultDTO<Student> result  = new ResultDTO<>();
        try{
            Student student = studentService.findStudentById(id);
            if(student == null){
                result.setResult(false);
                result.setError("user is not exist");
                return result;
            }
            result.setResult(true);
            result.setObject(student);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 通过学生邮箱查找某个学生
     * @param email
     * @return ResultDTO<Student>
     */
    @GetMapping(value="/find/email")
    public ResultDTO<Student> getUser(@RequestParam("email") String email){
        ResultDTO<Student> result  = new ResultDTO<>();
        try{
            Student student = studentService.findStudentByEmail(email);
            if(student == null){
                result.setResult(false);
                result.setError("user is not exist");
                return result;
            }
            result.setResult(true);
            result.setObject(student);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 更新学生信息
     */
    @PutMapping(value="/update")
    public ResultDTO<Student> updateUser(@RequestParam("id") long id,
                                         @RequestParam("email") String email,
                                         @RequestParam("name") String name,
                                         @RequestParam("password") String password){
//        Student student = new Student(); new 的方法似乎会把原来的grade等没有更新的属性抹掉
        ResultDTO<Student> result  = new ResultDTO<>();
        try{
            Student new_student = studentService.findStudentById(id);
            if(new_student == null){
                result.setResult(false);
                result.setError("user is not exist");
                return result;
            }

            new_student.setEmail(email);
            new_student.setName(name);
            new_student.setPassword(password);
            studentService.update(new_student);
            result.setResult(true);
            result.setObject(new_student);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }

    }

    /**
     * 通过年级查找学生
     * @param grade
     * @return List<Student>
     */
    @GetMapping(value = "/find/grade")
    public List<Student> getUserByGrade(@RequestParam("stgrade") int grade){
        return studentService.findStudentByGrade(grade);
    }

    @GetMapping(value = "/find/grade/class")
    public List<Student> getUserByGradeAndClass(@RequestParam("stgrade") int stGrade,
                                                @RequestParam("stclass") int stClass){
        return studentService.findStudentByGradeAndClass(stGrade,stClass);
    }

}
