package cn.edu.nju.onlineexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by user on 2017/11/20
 * .
 */
@Entity
public class Relation_StudentAndExam {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private long stId;

    @Column
    private long examineId;

    /**
     * 考试状态
     * 0表示未开始
     * 1表示进行中
     * 2表示已结束
     * 3表示已弃考
     */
    @Column
    private int status;

    public Relation_StudentAndExam(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStId() {
        return stId;
    }

    public void setStId(long stId) {
        this.stId = stId;
    }

    public long getExamineId() {
        return examineId;
    }

    public void setExamineId(long examineId) {
        this.examineId = examineId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
