package by.novik.hibernatestudents.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "students_hybernate")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int course;
    private String faculty;


}
