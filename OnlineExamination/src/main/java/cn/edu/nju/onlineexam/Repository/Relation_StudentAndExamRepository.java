package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Relation_StudentAndExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Relation_StudentAndExamRepository extends JpaRepository<Relation_StudentAndExam,Long>{

    public List<Relation_StudentAndExam> findByStId(long studentId);

    public List<Relation_StudentAndExam> findByExamineId(long examId);

    public Relation_StudentAndExam findByStIdAndExamineId(long studentId, long examId);
}
