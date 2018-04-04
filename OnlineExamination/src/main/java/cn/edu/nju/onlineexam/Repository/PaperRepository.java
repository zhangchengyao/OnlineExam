package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper,Long>{
    public Paper findByExamIdAndStId(long examId, long studentId);
}
