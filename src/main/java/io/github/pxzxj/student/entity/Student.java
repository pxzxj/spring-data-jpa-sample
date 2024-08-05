package io.github.pxzxj.student.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "student_family", joinColumns = @JoinColumn(name = "student_id"))
    private Set<Family> families;

    @Embeddable
    @Data
    public static class Family {

        private String name;

        private Integer age;

        private String relation;

    }

}
