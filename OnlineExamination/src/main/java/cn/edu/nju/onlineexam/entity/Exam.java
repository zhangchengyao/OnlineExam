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
public class Exam {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 考试名称
     */
    @Column
    private String name;

    /**
     * 试题数目
     */
    @Column
    private int number;

    /**
     * 每题分值
     */
    @Column
    private int value;

    /**
     * 考试开始时间
     */
    @Column
    private String start_time;

    /**
     * 考试结束时间
     */
    @Column
    private String end_time;

    /**
     * 发起这场考试的老师id
     */
    @Column
    private long teacherId;

    /**
     * 考试状态
     * 0表示未开始
     * 1表示进行中
     * 2表示已结束
     * 3表示已弃考
     */
    @Column
    private int status;

    public Exam() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
