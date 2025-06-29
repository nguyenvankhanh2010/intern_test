package com.example.test.demo.Database;

import com.example.test.demo.Model.Student;
import com.example.test.demo.Repository.StudentRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CsvDataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load từ classpath
        InputStream is = getClass()
                          .getClassLoader()
                          .getResourceAsStream("diem_thi_thpt_2024.csv");
        if (is == null) {
            throw new FileNotFoundException(
              "Không tìm thấy diem_thi_thpt_2024.csv trên classpath");
        }

        try (
          CSVReader csvReader = new CSVReader(
            new BufferedReader(
              new InputStreamReader(is, StandardCharsets.UTF_8)
            )
          )
        ) {
            csvReader.readNext(); // skip header
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                Student student = new Student();
                // ... parse record như trước
                studentRepository.save(student);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
