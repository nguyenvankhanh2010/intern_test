package com.example.test.demo.Service;

import com.example.test.demo.Model.Student;
import com.example.test.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> findByRegistrationNumber(String registrationNumber) {
        System.out.println("Searching for: " + registrationNumber);
        if (registrationNumber.length() < 8)
            registrationNumber = '0' + registrationNumber;
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    public List<Student> findTop10GroupA() {
        return studentRepository.findTop10ByGroupA(PageRequest.of(0, 10));
    }

    public Map<String, Map<String, Long>> getScoreDistribution() {
        Map<String, Map<String, Long>> distribution = new HashMap<>();
        String[] subjects = {"math", "physics", "chemistry", "history", "literature", "geography", "english", "biology", "civiceducation"};
        String[] ranges = {">=8", "6-8", "4-6", "<4"};

        for (String subject : subjects) {
            Map<String, Long> rangeCounts = new HashMap<>();
            for (String range : ranges) {
                rangeCounts.put(range, 0L);
            }

            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                Float score = getScoreBySubject(student, subject);
                if (score != null) {
                    if (score >= 8) {
                        rangeCounts.put(">=8", rangeCounts.get(">=8") + 1);
                    } else if (score >= 6) {
                        rangeCounts.put("6-8", rangeCounts.get("6-8") + 1);
                    } else if (score >= 4) {
                        rangeCounts.put("4-6", rangeCounts.get("4-6") + 1);
                    } else {
                        rangeCounts.put("<4", rangeCounts.get("<4") + 1);
                    }
                }
            }
            distribution.put(subject, rangeCounts);
        }
        return distribution;
    }

    private Float getScoreBySubject(Student student, String subject) {
        switch (subject.toLowerCase()) {
            case "math":
                return student.getMath();
            case "physics":
                return student.getPhysics();
            case "chemistry":
                return student.getChemistry();
            case "history":
                return student.getHistory();
            case "literature":
                return student.getLiterature();
            case "geography":
                return student.getGeography();
            case "english":
                return student.getEnglish();
            case "biology":
                return student.getBiology();
            case "civiceducation":
                return student.getCiviceducation();
            default:
                return null;
        }
    }
}