package cn.edu.nju.onlineexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 教师工号
     */
    @Column
    private String account;

    /**
     * 教师登录密码
     */
    @Column
    private String password;

    /**
     * 教师所教课程
     */
    @Column
    private String courses;

    public Teacher(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }
}
