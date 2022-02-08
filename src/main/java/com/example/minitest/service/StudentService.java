package com.example.minitest.service;

import com.example.minitest.model.Student;
import com.example.minitest.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{

    @Autowired
    StudentRepo studentRepo;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void delete(int id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepo.findById(id);
    }

    @Override
    public Page<Student> findByName(Pageable pageable,String name) {
        return (Page<Student>) studentRepo.findAllByNameContaining(pageable, name);
    }
}
