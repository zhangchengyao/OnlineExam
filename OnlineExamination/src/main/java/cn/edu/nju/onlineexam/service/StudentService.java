package cn.edu.nju.onlineexam.service;

import cn.edu.nju.onlineexam.Exception.LoginInvalidException;
import cn.edu.nju.onlineexam.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/11/20.
 *
 */

public interface StudentService {
    public Student register(Student st);
    public void delete(Long id);
    public Student update(Student st);
    public Student findStudentById(long id);
    public Student findStudentByEmail(String email);
    public List<Student> findStudentByGrade(int s_grade);
    public List<Student> findStudentByGradeAndClass(int s_grade, int s_class);
    public Student login(String email,String password) throws LoginInvalidException;
}
