package io.github.pxzxj.student.service;

import io.github.pxzxj.student.entity.Dog;
import io.github.pxzxj.student.entity.Dog_;
import io.github.pxzxj.student.entity.Student;
import io.github.pxzxj.student.entity.Student_;
import io.github.pxzxj.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final TransactionTemplate transactionTemplate;

    public StudentService(StudentRepository studentRepository, TransactionTemplate transactionTemplate) {
        this.studentRepository = studentRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public Page<Student> page(String name, Student.Gender gender, Integer ageStart, String nickname, String dogName, Pageable pageable) {
        Specification<Student> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(name)) {
                predicates.add(criteriaBuilder.like(root.get(Student_.NAME), "%" + name + "%"));
            }
            if (gender != null) {
                predicates.add(criteriaBuilder.equal(root.get(Student_.GENDER), gender));
            }
            if (ageStart != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get(Student_.AGE), ageStart));
            }
            if (StringUtils.hasText(nickname)) {
                Subquery<Long> subquery = criteriaBuilder.createQuery(Student.class).subquery(Long.class);
                Root<Student> subRoot = subquery.from(Student.class);
                subquery.select(criteriaBuilder.literal(1L))
                        .where(criteriaBuilder.equal(root.get(Student_.ID), subRoot.get(Student_.ID)),
                                criteriaBuilder.like(subRoot.join(Student_.NICKNAMES), "%" + nickname + "%"));
                predicates.add(criteriaBuilder.exists(subquery));
            }
            if (StringUtils.hasText(dogName)) {
                predicates.add(criteriaBuilder.like(root.join(Student_.DOGS, JoinType.LEFT).get(Dog_.NAME), "%" + dogName + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return studentRepository.findAll(specification, pageable);
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

    /**
     * Student使用@OneToMany注解引用了Dog并且使用了默认的FetchType.LAZY
     * 在Service方法中可以在查询Student后直接使用getDogs()方法获取dogs
     * @param id
     */
    public void findDogsSize(Long id) {
        Student student = studentRepository.findById(id).get();
        Set<Dog> dogs = student.getDogs();
        System.out.println("findDogsSizeInThread: " + dogs.size());
    }

    /**
     * 把findDogsSize的代码原封不动地放在线程中执行就会出错
     * @param id
     */
    public void findDogsSizeInThread(Long id) {
        new Thread(() -> {
            Student student = studentRepository.findById(id).get();
            Set<Dog> dogs = student.getDogs();
            System.out.println("findDogsSizeInThread: " + dogs.size());
        }).start();
    }

    /**
     * 把findDogsSize的代码原封不动地放在线程中并且在同一事务中执行就不会出错
     * @param id
     */
    public void findDogsSizeInThreadWithTransaction(Long id) {
        new Thread(() -> transactionTemplate.executeWithoutResult(ts -> {
            Student student = studentRepository.findById(id).get();
            Set<Dog> dogs = student.getDogs();
            System.out.println("findDogsSizeInThread: " + dogs.size());
        })).start();
    }


    public void updateAfterSleep() throws InterruptedException {
        Student student = studentRepository.findById(1L).get();
        student.setAge(student.getAge() + 1);
        TimeUnit.SECONDS.sleep(10);
        studentRepository.save(student);
    }

    public void updateImmediately() {
        Student student = studentRepository.findById(1L).get();
        student.setAge(student.getAge() + 1);
        studentRepository.save(student);
    }

}













