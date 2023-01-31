package by.novik.hibernatedemo.entity.student;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Table(name="studentas")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST) //без этого не работает
    @JoinColumn //добавляет колонку, можно добавить (name="rdgthfy")
    private Person person; //один студент - один человек, добавляет ай дистудента в табл, связывает их

    private int course;
    private String faculty;
    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) //у одного студента много сабжектов, которые можно собрать в лист
    //в Subject есть поле студент вот с ним связь, вместо joincolumn
    //каскад тупе персист: если студента удаляют, то и курсы удаляют,больше некому туда ходить
    //fetch eager по дефолту лейзи тянет сабжектов если ты попросишь гет стьюдента дать ге сабжект лист, не делает линего. игер тянет все
    //но больше исп в spring data
//    private List<Subject> subjectList; //неважно лист сет тд. так правильней:
    private List<Subject> subjectList=new ArrayList<>();
    public void addSubject(Subject subject) {
        subjectList.add(subject);
        subject.setStudent(this);
    }
}
