package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Relation_PaperAndProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Relation_PaperAndProblemRepository extends JpaRepository<Relation_PaperAndProblem,Long>{
    public List<Relation_PaperAndProblem> findByPaperIdAndStudentId(long paperId, long stId);
    public List<Relation_PaperAndProblem> findByPaperId(long paperId);
    public Relation_PaperAndProblem findByProblemId(long problemId);
}
