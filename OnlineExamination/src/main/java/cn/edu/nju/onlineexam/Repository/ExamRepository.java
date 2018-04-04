package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Long>{

    public Exam findByName(String name);

    public List<Exam> findByTeacherId(long teacherId);
}
