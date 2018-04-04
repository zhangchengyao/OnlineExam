package cn.edu.nju.onlineexam.DTO;

import cn.edu.nju.onlineexam.entity.Problem;

import java.util.List;

public class PaperDTO {
    private String examName;

    private String studentName;

    private String studentNo;

    private List<Problem> problems;

    private String studentAnswers;

    private int mark;

    private int value;


    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String name) {
        this.examName = name;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
