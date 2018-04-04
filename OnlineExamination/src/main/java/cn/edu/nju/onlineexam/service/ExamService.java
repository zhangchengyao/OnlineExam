package cn.edu.nju.onlineexam.service;

import cn.edu.nju.onlineexam.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExamService {

    public Paper findPaperById(long id);

    public Exam findExamById(long id);

    public int setExamStatus(long studentId,long examId, int mark);

    public ExamResult setExamResult(long studentId, long examId, String examName, String studentAnswers);

    public void sendExamCode(String password, String email) throws Exception;

    public void sendExamResult(ExamResult examResult, String email) throws Exception;

    public String getExamCode(long studentId, long examId);

    public Exam generateExam(Exam exam);

    public List<Problem> generateExamProblems(long examId);

    public List<Problem> generateExamProblems(long examId, long studentId);

    public List<Problem> resumeExamProblems(long examId, long studentId);

    public ExamResult generateExamResult(long examId, long studentId,
                                         String problems, String choices, String answers,
                                         int mark);

    public List<Exam> findExamsByTeacherId(long teacherId);

    public List<Exam> findExamsByStudentId(long studentId);

    public void addStudentToExam(List<Student> students, String examName);

    public List<Problem> addProblem(long examId, List<Problem> problems);

    public void deleteProblem(long id);

    public ExamResult getExamResult(long examId, long studentId);

    public void deleteExam(long id);
}
