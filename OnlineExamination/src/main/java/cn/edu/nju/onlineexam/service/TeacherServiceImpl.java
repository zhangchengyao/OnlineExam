package cn.edu.nju.onlineexam.service;

import cn.edu.nju.onlineexam.Exception.LoginInvalidException;
import cn.edu.nju.onlineexam.Repository.TeacherRepository;
import cn.edu.nju.onlineexam.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher register(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.delete(id);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return teacherRepository.findOne(id);
    }

    @Override
    public Teacher findTeacherByAccount(String account) {
        return teacherRepository.findByAccount(account);
    }

    @Override
    public Teacher login(String account, String password) throws LoginInvalidException {
        Teacher teacher = teacherRepository.findByAccount(account);
        if(teacher == null)
            throw new LoginInvalidException("email is not valid");
        if(!password.equals(teacher.getPassword()))
            throw new LoginInvalidException("password is not valid");
        return teacher;
    }
}
