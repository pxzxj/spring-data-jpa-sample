package io.github.pxzxj.student.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @ElementCollection
    @CollectionTable(name = "student_nickname", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "nickname")
    private Set<String> nicknames = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "student_family", joinColumns = @JoinColumn(name = "student_id"))
    private Set<Family> families;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Set<Dog> dogs = new HashSet<>();

    @CreatedDate
    private LocalDateTime createTime;

    @CreatedBy
    private String creator;

    @LastModifiedDate
    private LocalDateTime modifyTime;

    @LastModifiedBy
    private String modifier;

    @Version
    private Integer version;

    public enum Gender {

        BOY, GIRL;
    }

    @Embeddable
    @Data
    public static class Family {

        private String name;

        private Integer age;

        private String relation;

    }

}
