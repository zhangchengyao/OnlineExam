package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{
    public Teacher findByAccount(String account);
}
