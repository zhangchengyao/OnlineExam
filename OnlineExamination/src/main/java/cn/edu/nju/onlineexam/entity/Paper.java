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
public class Paper {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 本场考试的id
     */
    @Column
    private long examId;

    /**
     * 参加这场考试的学生id
     */
    @Column
    private long stId;

    /**
     * 该学生的这场考试的密码
     */
    @Column
    private String code;

    public Paper() {
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getStId() {
        return stId;
    }

    public void setStId(long stId) {
        this.stId = stId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
