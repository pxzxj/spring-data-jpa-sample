package io.github.pxzxj.student.controller;

import io.github.pxzxj.student.entity.Student;
import io.github.pxzxj.student.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @RequestMapping("/page")
    public Page<Student> page(String name, Student.Gender gender, Integer ageStart, String nickname, String dogName, Pageable pageable) {
        return studentService.page(name, gender, ageStart, nickname, dogName, pageable);
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

    @RequestMapping("/findDogsSize")
    public void findDogsSize(Long id) {
        studentService.findDogsSize(id);
    }

    @RequestMapping("/findDogsSizeInThread")
    public void findDogsSizeInThread(Long id) {
        studentService.findDogsSizeInThread(id);
    }

    @RequestMapping("/findDogsSizeInThreadWithTransaction")
    public void findDogsSizeInThreadWithTransaction(Long id) {
        studentService.findDogsSizeInThreadWithTransaction(id);
    }

    @RequestMapping("/updateAfterSleep")
    public void updateAfterSleep() throws InterruptedException {
        studentService.updateAfterSleep();
    }

    @RequestMapping("/updateImmediately")
    public void updateImmediately() {
        studentService.updateImmediately();
    }

}
