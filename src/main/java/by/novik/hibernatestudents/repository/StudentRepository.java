package by.novik.hibernatedemo.repository;


import by.novik.hibernatedemo.entity.student.Student;
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
            if (student.getId() == null) {
                session.persist(student);
            } else {
                session.merge(student);
            }
            transaction.commit();
        }
        return student;
    }


    public Optional<Student> findById(Long id) {
        Student student = null;
        try (Session session = factory.openSession()) {
            student = session.find(Student.class, id);
        }
        return Optional.ofNullable(student); //может быть ноль мб и не ноль
    }


    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            findById(id).ifPresent(student -> session.remove(student));
            // findById(id).ifPresent(session::remove); более современно
            transaction.commit();
        }
    }
}
