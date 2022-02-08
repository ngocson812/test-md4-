package com.example.minitest.controller;

import com.example.minitest.model.ClassRoom;
import com.example.minitest.model.Student;
import com.example.minitest.service.IClassRoomService;
import com.example.minitest.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    IStudentService iStudentService;

    @Autowired
    IClassRoomService iClassRoomService;

    @GetMapping("/home")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        modelAndView.addObject("student", iStudentService.findAll(PageRequest.of(page, 5)));
        modelAndView.addObject("classroom", iClassRoomService.findAll());
        return modelAndView;
    }


    @ModelAttribute("student")
    public Student student() {
        return new Student();
    }

    @ModelAttribute("classRoom")
    public List<ClassRoom> classRoom() {
        return iClassRoomService.findAll();
    }


    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }


    @PostMapping("/create")
    public Object create(@ModelAttribute(value = "student") Student student, @RequestParam int idclassroom) {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setIdclass(idclassroom);
        student.setClassRoom(classRoom);
        iStudentService.save(student);
        return "redirect:/home";
    }

    @GetMapping("edit/{id}")
    public ModelAndView editForm(@PathVariable int id) {
        Optional<Student> student = iStudentService.findById(id);
        List<ClassRoom> list = iClassRoomService.findAll();
        ModelAndView modelAndView = new ModelAndView("/student/edit");
        modelAndView.addObject("edit", student);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@ModelAttribute(value = "student") Student student, @RequestParam int idclass) {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setIdclass(idclass);
        student.setClassRoom(classRoom);
        iStudentService.save(student);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteForm(@PathVariable int id) {
        Optional<Student> student = iStudentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/student/delete");
        modelAndView.addObject("delete", student);
        return modelAndView;
    }

    @PostMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id, @ModelAttribute Student student) {
        iStudentService.delete(id);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/search")
    public ModelAndView find(@RequestParam(defaultValue = "0") int page, @RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        modelAndView.addObject("student",iStudentService.findByName(PageRequest.of(page, 5), name));
        return modelAndView;
    }

}
