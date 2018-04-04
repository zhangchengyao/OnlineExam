package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult,Long>{
    public ExamResult findByExamIdAndStudentId(long examId, long studentId);
}
