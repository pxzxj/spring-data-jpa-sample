package io.github.pxzxj.student.repository;

import io.github.pxzxj.student.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByNameLike(String name);

}
