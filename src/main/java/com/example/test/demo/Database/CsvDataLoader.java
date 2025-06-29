package com.example.test.demo.Database;

import com.example.test.demo.Model.Student;
import com.example.test.demo.Repository.StudentRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; // Thêm import này

@Component
public class CsvDataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource("diem_thi_thpt_2024.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVReader csvReader = new CSVReader(br)) {
            csvReader.readNext(); // Skip header
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                Student student = new Student();
                student.setRegistrationNumber(record[0]);
                student.setMath(record[1].isEmpty() ? null : Float.parseFloat(record[1]));
                student.setLiterature(record[2].isEmpty() ? null : Float.parseFloat(record[2]));
                student.setEnglish(record[3].isEmpty() ? null : Float.parseFloat(record[3]));
                student.setPhysics(record[4].isEmpty() ? null : Float.parseFloat(record[4]));
                student.setChemistry(record[5].isEmpty() ? null : Float.parseFloat(record[5]));
                student.setBiology(record[6].isEmpty() ? null : Float.parseFloat(record[6]));
                student.setHistory(record[7].isEmpty() ? null : Float.parseFloat(record[7]));
                student.setGeography(record[8].isEmpty() ? null : Float.parseFloat(record[8]));
                student.setCiviceducation(record[9].isEmpty() ? null : Float.parseFloat(record[9]));

                studentRepository.save(student);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
