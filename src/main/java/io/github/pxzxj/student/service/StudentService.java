package io.github.pxzxj.student.service;

import io.github.pxzxj.student.entity.Student;
import io.github.pxzxj.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}













