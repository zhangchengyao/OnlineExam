package cn.edu.nju.onlineexam.service;

import cn.edu.nju.onlineexam.Repository.*;
import cn.edu.nju.onlineexam.Util.GenerateExamCodeUtil;
import cn.edu.nju.onlineexam.Util.MailUtil;
import cn.edu.nju.onlineexam.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    ExamResultRepository examResultRepository;

    @Autowired
    Relation_StudentAndExamRepository relation_studentAndExamRepository;

    @Autowired
    Relation_PaperAndProblemRepository relation_paperAndProblemRepository;

    @Autowired
    Relation_ExamAndProblemRepository relation_examAndProblemRepository;

    @Override
    public Paper findPaperById(long id) {
        return paperRepository.findOne(id);
    }

    @Override
    public Exam findExamById(long id) {
        return examRepository.findOne(id);
    }

    @Override
    public int setExamStatus(long studentId, long examId, int mark) {
        Relation_StudentAndExam relation_studentAndExam = relation_studentAndExamRepository.findByStIdAndExamineId(studentId,examId);
        relation_studentAndExam.setStatus(2);
        ExamResult examResult = examResultRepository.findByExamIdAndStudentId(examId,studentId);
        examResult.setMark(mark);
        return relation_studentAndExam.getStatus();
    }

    @Override
    public ExamResult setExamResult(long studentId, long examId, String examName, String studentAnswers) {
        ExamResult examResult = new ExamResult();
        Student student = studentRepository.findOne(studentId);
        examResult.setExamName(examName);
        examResult.setStudentId(studentId);
        examResult.setName(student.getName());
        examResult.setExamId(examId);
        examResult.setStudentAnswers(studentAnswers);
        examResultRepository.save(examResult);
        return examResult;
    }

    @Override
    public void sendExamCode(String password, String email) throws Exception{
        MailUtil.send(password,"exam password",email);
    }

    @Override
    public void sendExamResult(ExamResult examResult, String email) throws Exception {
        MailUtil.send(examResult.toString(),"exam result",email);
    }

    @Override
    public String getExamCode(long studentId, long examId) {
        Paper paper = paperRepository.findByExamIdAndStId(examId,studentId);
        return paper.getCode();
    }

    @Override
    public Exam generateExam(Exam exam) {
        return examRepository.save(exam);
    }

    /**
     * 生成某场考试的题库
     * @param examId
     * @return List<Problem>
     */
    @Override
    public List<Problem> generateExamProblems(long examId) {
        List<Relation_ExamAndProblem> thisExam = relation_examAndProblemRepository.findByExamId(examId);
        List<Problem> allProblems = new ArrayList<>();
        for(Relation_ExamAndProblem relation_examAndProblem:thisExam){
            allProblems.add(problemRepository.findOne(relation_examAndProblem.getProblemId()));
        }
        return allProblems;
    }

    /**
     * 生成某个学生的试卷
     * @param examId
     * @param studentId
     * @return List<Problem>
     */
    @Override
    public List<Problem> generateExamProblems(long examId, long studentId) {
//        Relation_StudentAndExam relation_studentAndExam = new Relation_StudentAndExam();
//        relation_studentAndExam.setExamineId(examId);
//        relation_studentAndExam.setStId(studentId);
//        relation_studentAndExam.setStatus(-1);
//        relation_studentAndExamRepository.save(relation_studentAndExam);

        Exam exam = examRepository.findOne(examId);
        int number = exam.getNumber();
        List<Relation_ExamAndProblem> relation_examAndProblems = relation_examAndProblemRepository.findByExamId(examId);
        //problems 这场考试的题库
        List<Problem> problems = new ArrayList<>();
        for(Relation_ExamAndProblem relation_examAndProblem:relation_examAndProblems){
            problems.add(problemRepository.findOne(relation_examAndProblem.getProblemId()));
        }

        //selectedProblems 这份试卷从题库中抽到的题目
        List<Problem> selectedProblems = new ArrayList<>();
        int totalNum = problems.size();

        for(int i=0;i<number;i++){
            int index = (int)Math.round(Math.random()*(totalNum-1));
            Problem problem = problems.get(index);
            if(!selectedProblems.contains(problem)){
                selectedProblems.add(problem);
            }else{
                i--;
            }
        }

        //打乱试卷题目顺序
        for(Problem problem:selectedProblems){
            String A = problem.getA();
            String B = problem.getB();
            String C = problem.getC();
            String D = problem.getD();
            String[] choices = {A,B,C,D};
            String answer = problem.getAnswer();
            char[] order = {'#','#','#','#'};
            char[] original = {'A','B','C','D'};
            int i = 0;
            while(i<3){
                int index = (int)Math.round(Math.random()*3);
                if(order[index]=='#'){
                    order[index] = original[i];
                    i++;
                }
            }
            for(int j=0;j<order.length;j++){
                if(order[j]=='#'){
                    order[j] = original[3];
                }
            }
            StringBuilder newAnswer = new StringBuilder();
            for(int j=0;j<answer.length();j++){
                for(int k=0;k<order.length;k++){
                    if(answer.charAt(j)==order[k]){
                        newAnswer.append(original[k]);
                    }
                }
            }
            problem.setA(choices[order[0]-'A']);
            problem.setB(choices[order[1]-'A']);
            problem.setC(choices[order[2]-'A']);
            problem.setD(choices[order[3]-'A']);
            problem.setAnswer(newAnswer.toString());
        }

        List<Relation_PaperAndProblem> relations = relation_paperAndProblemRepository.findByPaperIdAndStudentId(examId,studentId);
        if(relations==null || relations.size()==0){
            //把这张试卷上的题目存到数据库中
            for(Problem problem:selectedProblems){
                System.out.println(problem.getId());
                Relation_PaperAndProblem relation_paperAndProblem = new Relation_PaperAndProblem();
                relation_paperAndProblem.setPaperId(examId);
                relation_paperAndProblem.setStudentId(studentId);
                relation_paperAndProblem.setProblemId(problem.getId());
                relation_paperAndProblemRepository.save(relation_paperAndProblem);
            }
        }

        return selectedProblems;
    }

    @Override
    public List<Problem> resumeExamProblems(long examId, long studentId) {
        List<Relation_PaperAndProblem> relations = relation_paperAndProblemRepository.findByPaperIdAndStudentId(examId,studentId);
        List<Problem> problems = new ArrayList<>();
        for(Relation_PaperAndProblem relation_paperAndProblem:relations){
            System.out.println(relation_paperAndProblem.getProblemId());
            problems.add(problemRepository.findOne(relation_paperAndProblem.getProblemId()));
        }
        return problems;
    }


    @Override
    public ExamResult generateExamResult(long examId, long studentId,
                                         String problems, String choices, String answers,
                                         int mark){
        ExamResult examResult = new ExamResult();
        StudentService studentService = new StudentServiceImpl();
        Student student = studentService.findStudentById(studentId);
        examResult.setStudentId(studentId);
        examResult.setEmail(student.getEmail());
        examResult.setName(student.getName());
        examResult.setProblems(problems);
        examResult.setChoices(choices);
        examResult.setStudentAnswers(answers);
        examResult.setMark(mark);
        try {
            sendExamResult(examResult,student.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examResultRepository.save(examResult);
    }

    @Override
    public List<Exam> findExamsByTeacherId(long teacherId) {
        return examRepository.findByTeacherId(teacherId);
    }

    @Override
    public List<Exam> findExamsByStudentId(long studentId) {
        List<Relation_StudentAndExam> relations = relation_studentAndExamRepository.findByStId(studentId);
        List<Exam> exams = new ArrayList<>();
        for(Relation_StudentAndExam relation:relations){
            long examId = relation.getExamineId();
            Exam exam = examRepository.findOne(examId);
            String start = exam.getStart_time();
            String end = exam.getEnd_time();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String current = df.format(new Date());
            ExamResult hasResult = examResultRepository.findByExamIdAndStudentId(examId,studentId);
            if(hasResult!=null || compareDate(current,end)>0){
                exam.setStatus(2);
            }else if(compareDate(current,start)>0){
                Relation_StudentAndExam relation_studentAndExam = relation_studentAndExamRepository.findByStIdAndExamineId(studentId,examId);
                if(relation_studentAndExam.getStatus()==2){
                    exam.setStatus(2);
                }else{
                    exam.setStatus(1);
                }
            }else{
                exam.setStatus(0);
            }
            // 给exam设置分数，已结束的从exam_result里取得成绩，其他的分数就是
            if(exam.getStatus()==2){
                ExamResult examResult = examResultRepository.findByExamIdAndStudentId(examId,studentId);
                if(examResult!=null){
                    exam.setValue(examResult.getMark());
                }
                else{
                    exam.setValue(-1);
                }
            }else{
                exam.setValue(-1);
            }
            exams.add(exam);
        }
        return exams;
    }

    @Override
    public void addStudentToExam(List<Student> students, String examName) {
        if(students==null)
            return ;
        Exam exam = examRepository.findByName(examName);
        for(Student student:students){
            String examCode = GenerateExamCodeUtil.getRandomString(11);
            try {
                sendExamCode(examCode,student.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Paper exist = paperRepository.findByExamIdAndStId(exam.getId(),student.getId());
            if(exist==null){
                Paper paper = new Paper();
                paper.setExamId(exam.getId());
                paper.setStId(student.getId());
                paper.setCode(examCode);
                paperRepository.save(paper);
            }


            Relation_StudentAndExam relation_studentAndExam = new Relation_StudentAndExam();
            relation_studentAndExam.setStId(student.getId());
            relation_studentAndExam.setExamineId(exam.getId());
            relation_studentAndExamRepository.save(relation_studentAndExam);
        }
    }

    @Override
    public List<Problem> addProblem(long examId, List<Problem> problems) {
        List<Problem> res = new ArrayList<>();
        for(Problem problem:problems){
            Problem savedProblem = problemRepository.save(problem);
            Relation_ExamAndProblem relation_examAndProblem = new Relation_ExamAndProblem();
            relation_examAndProblem.setExamId(examId);
            relation_examAndProblem.setProblemId(savedProblem.getId());
            relation_examAndProblemRepository.save(relation_examAndProblem);
            res.add(savedProblem);
        }
        return res;
    }

    @Override
    public void deleteProblem(long id) {
        Relation_ExamAndProblem relation_examAndProblem = relation_examAndProblemRepository.findByProblemId(id);
        relation_examAndProblemRepository.delete(relation_examAndProblem.getId());
        Relation_PaperAndProblem relation_paperAndProblem = relation_paperAndProblemRepository.findByProblemId(id);
        relation_paperAndProblemRepository.delete(relation_paperAndProblem.getId());
        problemRepository.delete(id);
    }

    @Override
    public ExamResult getExamResult(long examId, long studentId) {
        return examResultRepository.findByExamIdAndStudentId(examId,studentId);
    }

    @Override
    public void deleteExam(long id) {
        examRepository.delete(id);
        List<Relation_ExamAndProblem> relation_examAndProblems = relation_examAndProblemRepository.findByExamId(id);
        for(Relation_ExamAndProblem relation_examAndProblem:relation_examAndProblems){
            relation_examAndProblemRepository.delete(relation_examAndProblem.getId());
        }
        List<Relation_StudentAndExam> relation_studentAndExams = relation_studentAndExamRepository.findByExamineId(id);
        for(Relation_StudentAndExam relation_studentAndExam:relation_studentAndExams){
            relation_studentAndExamRepository.delete(relation_studentAndExam.getId());
        }
        List<Relation_PaperAndProblem> relation_paperAndProblems = relation_paperAndProblemRepository.findByPaperId(id);
        for(Relation_PaperAndProblem relation_paperAndProblem:relation_paperAndProblems){
            relation_paperAndProblemRepository.delete(relation_paperAndProblem.getId());
        }
    }

    private int compareDate(String date1, String date2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                //date1在date2之后
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //date1在date2之前
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
