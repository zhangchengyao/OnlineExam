package cn.edu.nju.onlineexam.Repository;

import cn.edu.nju.onlineexam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    public Student findByEmail(String email);

    public List<Student> findByGrade(int grade);

    public List<Student> findByGradeAndStudentClass(int grade, int studentClass);
}
