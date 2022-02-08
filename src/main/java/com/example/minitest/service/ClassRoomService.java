package com.example.minitest.service;

import com.example.minitest.model.ClassRoom;
import com.example.minitest.repository.ClassRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomService implements IClassRoomService{

    @Autowired
    ClassRoomRepo classRoomRepo;

    @Override
    public List<ClassRoom> findAll() {
        return (List<ClassRoom>) classRoomRepo.findAll();
    }
}
