package com.example.minitest.service;

import com.example.minitest.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    public Page<Student> findAll(Pageable pageable);
    public void save(Student student);
    public void delete(int id);
    public Optional<Student> findById(int id);
    public Page<Student> findByName(Pageable pageable, String name);
 }
