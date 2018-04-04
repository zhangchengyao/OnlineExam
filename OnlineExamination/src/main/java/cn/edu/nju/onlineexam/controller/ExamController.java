package cn.edu.nju.onlineexam.controller;

import cn.edu.nju.onlineexam.DTO.PaperDTO;
import cn.edu.nju.onlineexam.DTO.ResultDTO;
import cn.edu.nju.onlineexam.Util.ParseExcelUtil;
import cn.edu.nju.onlineexam.entity.*;
import cn.edu.nju.onlineexam.service.ExamService;
import cn.edu.nju.onlineexam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;

    /**
     * 将学生访问考试的密码发送到学生邮箱
     * @param id
     * @return ResultDTO<Exam>
     */
    @GetMapping(value="/send/code")
    public  ResultDTO<Exam> sendExamCode(@RequestParam("id") long id,
                                         @RequestParam("paperId") long paperId){

        ResultDTO<Exam> result = new ResultDTO<>();

        try {
            Student student = studentService.findStudentById(id);
            String email = student.getEmail();
            Paper paper = examService.findPaperById(paperId);
            String password = paper.getCode();
            examService.sendExamCode(password, email);
            result.setResult(true);
            return result;
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 将考试结果发送到学生邮箱
     * @param id
     * @return ResultDTO<ExamResult>
     * */
    @GetMapping(value="/send/result")
    public ResultDTO<ExamResult> sendExamResult(@RequestParam("id") long id){
        ResultDTO<ExamResult> result = new ResultDTO<>();
        try{
            Student student = studentService.findStudentById(id);
            String email = student.getEmail();
            examService.sendExamResult(result.getObject(),email);
            result.setResult(true);
        }catch (Exception e){
            result.setResult(false);
            result.setError(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 生成考试信息
     * 在生成考试页面点击确认后调用（通过上传名单）
     * @param teacherId
     * @param name
     * @param number
     * @param value
     * @param start
     * @param end
     * @param fileName
     * @return Exam
     */
    @PostMapping(value = "/generate/exam/file")
    public Exam generateExam(@RequestParam("teacherid") long teacherId,
                             @RequestParam("name") String name,
                             @RequestParam("number") int number,
                             @RequestParam("value") int value,
                             @RequestParam("start") String start,
                             @RequestParam("end") String end,
                             @RequestParam("filename") String fileName){
        Exam exam = new Exam();
        exam.setName(name);
        exam.setNumber(number);
        exam.setValue(value);
        exam.setStart_time(start);
        exam.setEnd_time(end);
        exam.setTeacherId(teacherId);

        ParseExcelUtil poi = new ParseExcelUtil();
        List<List<String>> content = poi.read(fileName);

        List<Student> students = new ArrayList<>();
        if(content!=null){
            for(List<String> row:content){
                if(row.get(0).equals("邮箱")){
                    continue;
                }
                Student student = studentService.findStudentByEmail(row.get(0));
                students.add(student);
            }
        }

        Exam result = examService.generateExam(exam);
        examService.addStudentToExam(students,name);

        return result;
    }

    /**
     * 生成考试信息
     * 在生成考试页面点击确认后调用（通过年级和班级）
     * @param teacherId
     * @param name
     * @param number
     * @param value
     * @param start
     * @param end
     * @param st_grade
     * @param st_class
     * @return Exam
     * */
    @PostMapping(value="/generate/exam")
    public Exam generateExam(@RequestParam("teacherid") long teacherId,
                             @RequestParam("name") String name,
                             @RequestParam("number") int number,
                             @RequestParam("value") int value,
                             @RequestParam("start") String start,
                             @RequestParam("end") String end,
                             @RequestParam("grade") String st_grade,
                             @RequestParam("class") String st_class){
        Exam exam = new Exam();
        exam.setName(name);
        exam.setNumber(number);
        exam.setValue(value);
        exam.setStart_time(start);
        exam.setEnd_time(end);
        exam.setTeacherId(teacherId);

        List<Student> students = null;
        if(!st_grade.equals("") && !st_class.equals("")){
            students = studentService.findStudentByGradeAndClass(Integer.parseInt(st_grade),Integer.parseInt(st_class));
        }else if(!st_grade.equals("")){
            students = studentService.findStudentByGrade(Integer.parseInt(st_grade));
        }

        Exam result = examService.generateExam(exam);
        examService.addStudentToExam(students,name);

        return result;
    }

    /**
     * 某一场考试的所有题目
     * 试题管理中点击查看后调用
     * @param examId
     * @return List<Problem>
     */
    @GetMapping(value = "/generate/allproblems")
    public List<Problem> generateExamProblems(@RequestParam("examid") long examId){
        System.out.println(examId);
        return examService.generateExamProblems(examId);
    }


    /**
     * 为某个学生生成考试试卷
     * @param examId
     * @param studentId
     * @return List<Problem>
     * */
    @GetMapping(value="/generate/problems")
    public List<Problem> generateExamProblems(@RequestParam("examid") long examId,
                                              @RequestParam("studentid") long studentId){

        return examService.generateExamProblems(examId,studentId);
    }

    /**
     * 获得某个学生某场考试的考试密码
     * @param studentId
     * @param examId
     * @return String
     */
    @GetMapping(value = "/get/exam/code")
    public String getExamCode(@RequestParam("studentid") long studentId,
                              @RequestParam("examid") long examId){
        return examService.getExamCode(studentId,examId);
    }

    /**
     * 从考试汇总页面返回时恢复考试题目
     * @param examId
     * @param studentId
     * @return
     */
    @GetMapping(value = "/resume/problems")
    public List<Problem> resumeExamProblems(@RequestParam("examid") long examId,
                                            @RequestParam("studentid") long studentId){
        return examService.resumeExamProblems(examId,studentId);
    }

    /**
     * 保存考试结果，生成成绩单
     * 考试页面点击提交试卷时调用
     * @param examId
     * @param studentId
     * @param problems
     * @param choices
     * @param answers
     * @param mark
     * @return ExamResult
     * */
    @PostMapping(value="/result")
    public ResultDTO<ExamResult> saveExamResult(@RequestParam("examid") long examId,
                                                @RequestParam("studentid") long studentId,
                                                @RequestParam("problems") String problems,
                                                @RequestParam("choices") String choices,
                                                @RequestParam("answers") String answers,
                                                @RequestParam("mark") int mark){
        ResultDTO<ExamResult> result = new ResultDTO<>();
        ExamResult examResult= examService.generateExamResult(examId,studentId,problems,choices,answers,mark);
        result.setObject(examResult);
        result.setResult(true);
        return result;
    }

    /**
     * 获得考生某场考试的成绩
     * @param examId
     * @param studentId
     * @param studentAnswers
     * @return String
     */
    @GetMapping(value = "/result/mark")
    public String getExamMark(@RequestParam("examid") long examId,
                           @RequestParam("studentid") long studentId,
                           @RequestParam("studentanswer") String studentAnswers){
        Exam exam = examService.findExamById(examId);
        int number = exam.getNumber();
        int value = exam.getValue();
        int totalMark = number * value;
        List<Problem> problems = examService.resumeExamProblems(examId,studentId);
        int i = 0;
        int right = 0;
        String[] studentAnswersArray = studentAnswers.split("~");
        for(Problem problem:problems){
            if(problem.getAnswer().equals(studentAnswersArray[i])){
                right++;
            }
            i++;
        }
        int mark = right * value;
        examService.setExamResult(studentId,examId,exam.getName(),studentAnswers);
        examService.setExamStatus(studentId,examId,mark);
        return mark+"/"+totalMark;
    }

    /**
     * 展示考试结果
     * 在我的考试页面点击查看考试时被调用
     * @param examId
     * @param studentId
     * @return ExamResult
     */
    @GetMapping(value = "/show/result")
    public ExamResult getExamResult(@RequestParam("examid") long examId,
                                    @RequestParam("studentid") long studentId){
        return examService.getExamResult(examId,studentId);
    }

    /**
     * 查找某个老师发起的所有考试
     * 在试题管理页面被调用
     * @param teacherId
     * @return List<Exam>
     */
    @GetMapping(value = "/show/teacher")
    public List<Exam> getExamsByTeacher(@RequestParam("teacherid") long teacherId){
        return examService.findExamsByTeacherId(teacherId);
    }

    /**
     * 查找某个学生参与的所有考试
     * @param studentId
     * @return List<Exam>
     */
    @GetMapping(value = "/show/student")
    public List<Exam> getExamsByStudent(@RequestParam("studentid") long studentId){
        return examService.findExamsByStudentId(studentId);
    }


    /**
     * 上传试题
     * 在上传试题界面点击确认后被调用
     * @param examId
     * @param fileName
     * @return List<Problem>
     */
    @PostMapping(value = "/add/problem")
    public List<Problem> addProblem(@RequestParam("examid") long examId,
                                    @RequestParam("filename") String fileName){
        List<Problem> problems = new ArrayList<>();
        ParseExcelUtil poi = new ParseExcelUtil();
        List<List<String>> content = poi.read(fileName);
        if(content==null){
            return null;
        }
        for(List<String> row:content){
            Problem problem = new Problem();
            problem.setContent(row.get(0));
            problem.setA(row.get(1));
            problem.setB(row.get(2));
            problem.setC(row.get(3));
            problem.setD(row.get(4));
            problem.setAnswer(row.get(5));
            problems.add(problem);
        }
        return examService.addProblem(examId,problems);
    }

    /**
     * 删除某个试题
     * @param id
     */
    @GetMapping(value = "/delete/problem")
    public void deleteProblem(@RequestParam("problemid") long id){
        examService.deleteProblem(id);
    }

    /**
     * 删除某场考试
     * @param id
     */
    @PostMapping(value = "/delete/exam")
    public void deleteExam(@RequestParam("examid") long id){
        examService.deleteExam(id);
    }

    @GetMapping(value = "/show/exam/detail")
    public PaperDTO getExamDetail(@RequestParam("studentid") long studentId,
                                  @RequestParam("examid") long examId){
        PaperDTO paperDTO = new PaperDTO();
        Student student = studentService.findStudentById(studentId);
        paperDTO.setStudentName(student.getName());
        paperDTO.setStudentNo(student.getStudentNo());
        List<Problem> problems = examService.resumeExamProblems(examId,studentId);
        paperDTO.setProblems(problems);
        ExamResult examResult = examService.getExamResult(examId,studentId);
        paperDTO.setExamName(examResult.getExamName());
        paperDTO.setMark(examResult.getMark());
        paperDTO.setStudentAnswers(examResult.getStudentAnswers());
        Exam exam = examService.findExamById(examId);
        paperDTO.setValue(exam.getValue());
        return paperDTO;
    }

}
