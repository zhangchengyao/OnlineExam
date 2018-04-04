package cn.edu.nju.onlineexam.service;

import cn.edu.nju.onlineexam.Exception.LoginInvalidException;
import cn.edu.nju.onlineexam.entity.Teacher;

public interface TeacherService {
    public Teacher register(Teacher teacher);
    public void delete(Long id);
    public Teacher update(Teacher teacher);
    public Teacher findTeacherById(Long id);
    public Teacher findTeacherByAccount(String account);
    public Teacher login(String email, String passWord) throws LoginInvalidException;
}
