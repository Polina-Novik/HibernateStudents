package by.novik.hibernatestudents.service;


import by.novik.hibernatestudents.entity.Student;
import by.novik.hibernatestudents.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor

public class StudentService {
    private final StudentRepository repository;

    public Student save(Student student) {

        return repository.save(student);
    }


    public Optional<Student> findById(Long id) {

        return repository.findById(id);
    }


    public List<Student> findAll() {

        return repository.findAll();
    }


    public void delete(Long id) {
        repository.delete(id);
    }


}
