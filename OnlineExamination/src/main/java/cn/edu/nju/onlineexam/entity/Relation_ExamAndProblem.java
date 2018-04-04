package cn.edu.nju.onlineexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Relation_ExamAndProblem {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 某场考试的id
     */
    @Column
    private long examId;

    /**
     * 该场考试的题目id
     */
    @Column
    private long problemId;

    public Relation_ExamAndProblem(){}

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
