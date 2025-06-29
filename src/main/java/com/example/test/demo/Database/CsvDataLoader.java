package com.example.test.demo.Database;

import com.example.test.demo.Model.Student;
import com.example.test.demo.Repository.StudentRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

@Component
public class CsvDataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource("diem_thi_thpt_2024.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVReader csvReader = new CSVReader(br)) {
            csvReader.readNext(); // Skip header
            String[] record;
            int batchSize = 100; // Số bản ghi mỗi batch
            int count = 0;
            while ((record = csvReader.readNext()) != null) {
                Student student = new Student();
                student.setRegistrationNumber(record[0]);
                student.setMath(record[1].isEmpty() ? 0f : Float.parseFloat(record[1]));
                student.setLiterature(record[2].isEmpty() ? 0f : Float.parseFloat(record[2]));
                student.setEnglish(record[3].isEmpty() ? 0f : Float.parseFloat(record[3]));
                student.setPhysics(record[4].isEmpty() ? 0f : Float.parseFloat(record[4]));
                student.setChemistry(record[5].isEmpty() ? 0f : Float.parseFloat(record[5]));
                student.setBiology(record[6].isEmpty() ? 0f : Float.parseFloat(record[6]));
                student.setHistory(record[7].isEmpty() ? 0f : Float.parseFloat(record[7]));
                student.setGeography(record[8].isEmpty() ? 0f : Float.parseFloat(record[8]));
                student.setCiviceducation(record[9].isEmpty() ? 0f : Float.parseFloat(record[9]));

                studentRepository.save(student);
                count++;
                if (count % batchSize == 0) {
                    studentRepository.flush(); // Flush để commit batch
                    studentRepository.clear(); // Clear persistence context
                }
            }
            studentRepository.flush(); // Flush lần cuối
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
