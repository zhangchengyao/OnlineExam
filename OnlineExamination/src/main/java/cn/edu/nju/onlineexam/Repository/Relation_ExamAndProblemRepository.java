package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Relation_ExamAndProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Relation_ExamAndProblemRepository extends JpaRepository<Relation_ExamAndProblem,Long>{
    public List<Relation_ExamAndProblem> findByExamId(long examId);

    public Relation_ExamAndProblem findByProblemId(long problemId);
}
