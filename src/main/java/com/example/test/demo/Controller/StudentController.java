package com.example.test.demo.Controller;


import com.example.test.demo.Model.Student;
import com.example.test.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("top10GroupA", studentService.findTop10GroupA());
        model.addAttribute("scoreDistribution", studentService.getScoreDistribution());
        model.addAttribute("isPostRequest", false); // Đặt mặc định là false cho GET
        return "index";
    }

    @PostMapping("/check-score")
    public String checkScore(@RequestParam String registrationNumber, Model model) {
        Optional<Student> student = studentService.findByRegistrationNumber(registrationNumber);
        model.addAttribute("student", student.orElse(null));
        model.addAttribute("isPostRequest", true); // Đặt là true cho POST
        model.addAttribute("top10GroupA", studentService.findTop10GroupA());
        model.addAttribute("scoreDistribution", studentService.getScoreDistribution());
        return "index";
    }
}