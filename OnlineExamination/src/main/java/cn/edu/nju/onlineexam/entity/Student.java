package cn.edu.nju.onlineexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by user on 2017/11/20.
 *
 */

@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 学生邮箱，登录时候的账号
     */
    @Column
    private String email;

    /**
     * 学生登录密码
     */
    @Column
    private String password;

    /**
     * 学生姓名
     */
    @Column
    private String name;

    /**
     * 学生的学号
     */
    @Column
    private String studentNo;

    /**
     * 学生所在的年级
     */
    @Column
    private int grade;

    /**
     * 学生所在的班级
     */
    @Column
    private int studentClass;

    public Student(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(int studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
