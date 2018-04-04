package cn.edu.nju.onlineexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExamResult {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 参加这场考试的学生id
     */
    @Column
    private long studentId;

    /**
     * 这场考试的id
     */
    @Column
    private long examId;

    /**
     * 这场考试的名称
     */
    @Column
    private String examName;

    /**
     * 该学生的邮箱
     */
    @Column
    private String email;

    /**
     * 参加这场考试的学生姓名
     */
    @Column
    private String name;

    /**
     * 这张试卷所有题目
     */
    @Column
    private String problems;

    /**
     * 这张试卷所有题目的备选答案
     */
    @Column
    private String choices;

    /**
     * 这张试卷的学生答案
     */
    @Column
    private String studentAnswers;

    /**
     * 该学生这场考试的成绩
     */
    @Column
    private int mark;

    public ExamResult(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(String studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", problems='" + problems + '\'' +
                ", choices='" + choices + '\'' +
                ", studentAnswers='" + studentAnswers + '\'' +
                ", mark=" + mark +
                '}';
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
