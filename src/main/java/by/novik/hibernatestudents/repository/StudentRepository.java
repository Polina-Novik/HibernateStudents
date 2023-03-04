package by.novik.hibernatestudents.repository;


import by.novik.hibernatestudents.entity.Student;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final SessionFactory factory;

    public Student save(Student student) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        }
        return student;
    }

    public List<Student> findAll() {
        List<Student> students;
        try (Session session = factory.openSession()) {
            students = session.createQuery("from Student", Student.class).getResultList();
        }
        return students;
    }

    public Optional<Student> findById(Long id) {
        Student student = null;
        try (Session session = factory.openSession()) {
            student = session.find(Student.class, id);
        }
        return Optional.ofNullable(student);
    }


    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            findById(id).ifPresent(student -> session.remove(student));
            transaction.commit();
        }
    }
}
