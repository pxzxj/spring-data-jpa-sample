package io.github.pxzxj.student.controller;

import io.github.pxzxj.student.entity.Student;
import io.github.pxzxj.student.service.StudentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/save")
    public void save(@RequestBody Student student) {
        studentService.save(student);
    }

    @RequestMapping("/findAll")
    public Iterable<Student> findAll() {
        return studentService.findAll();
    }

    @RequestMapping("/delete")
    public void delete(Long id) {
        studentService.delete(id);
    }

}
