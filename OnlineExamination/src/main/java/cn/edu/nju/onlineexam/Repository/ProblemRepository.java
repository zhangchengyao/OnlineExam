package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem,Long>{
}
