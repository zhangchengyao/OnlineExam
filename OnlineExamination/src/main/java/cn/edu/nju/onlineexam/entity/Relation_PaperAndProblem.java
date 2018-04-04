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
public class Relation_PaperAndProblem {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private long paperId;

    @Column
    private long studentId;

    @Column
    private long problemId;

    @Column
    private String stAnswer;

    public Relation_PaperAndProblem(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPaperId() {
        return paperId;
    }

    public void setPaperId(long paperId) {
        this.paperId = paperId;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

    public String getStAnswer() {
        return stAnswer;
    }

    public void setStAnswer(String stAnswer) {
        this.stAnswer = stAnswer;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
