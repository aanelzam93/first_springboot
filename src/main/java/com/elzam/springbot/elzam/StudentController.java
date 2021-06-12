package com.elzam.springbot.elzam;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {
    private  final StudentService studentServive;
    @GetMapping
    public List<Student> fetchAllStudent(){
        return  studentServive.getAllStudent();
    }
}
