package cn.edu.nju.onlineexam.service;


import cn.edu.nju.onlineexam.Exception.LoginInvalidException;
import cn.edu.nju.onlineexam.Repository.StudentRepository;
import cn.edu.nju.onlineexam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/11/20.
 *
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student register(Student st) {
        return studentRepository.save(st);
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(id);
    }

    @Override
    public Student update(Student st) {
        return studentRepository.save(st);
    }

    @Override
    public Student findStudentById(long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<Student> findStudentByGrade(int s_grade) {
        return studentRepository.findByGrade(s_grade);
    }

    @Override
    public List<Student> findStudentByGradeAndClass(int s_grade, int s_class) {
        return studentRepository.findByGradeAndStudentClass(s_grade,s_class);
    }

    @Override
    public Student login(String email, String password) throws LoginInvalidException{
        Student student = studentRepository.findByEmail(email);
        if(student == null)
            throw new LoginInvalidException("email is not valid");
        if(!password.equals(student.getPassword()))
            throw new LoginInvalidException("password is not valid");
        return student;
    }
}
