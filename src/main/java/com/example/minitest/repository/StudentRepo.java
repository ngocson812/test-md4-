package com.example.minitest.repository;

import com.example.minitest.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student, Integer> {
    Page<Student> findAllByNameContaining(Pageable pageable, String name);
}
