package io.github.pxzxj.student.repository;

import io.github.pxzxj.student.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void findByNameLike() {
        List<Student> students = studentRepository.findByNameLike("%T%");
        assertEquals(1, students.size());
    }

    @Test
    void updateAge() {
        int count = studentRepository.updateAge(1);
        assertEquals(1, count);
    }

}