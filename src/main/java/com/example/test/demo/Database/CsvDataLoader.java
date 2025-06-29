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

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CsvDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CsvDataLoader.class);

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        ClassPathResource resource = new ClassPathResource("diem_thi_thpt_2024.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVReader csvReader = new CSVReader(br)) {
            csvReader.readNext(); // Skip header
            String[] record;
            int batchSize = 50; // Số bản ghi mỗi batch
            int count = 0;
            int maxRecords = 1000; // Giới hạn số bản ghi để test
            while ((record = csvReader.readNext()) != null && count < maxRecords) {
                if (record.length < 10) {
                    logger.error("Invalid record at line {}: insufficient columns", count + 2);
                    continue; // Bỏ qua dòng không hợp lệ
                }
                try {
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
                        entityManager.flush();
                        entityManager.clear();
                        logger.info("Processed {} records", count);
                    }
                } catch (NumberFormatException e) {
                    logger.error("Number format error at line {}: {}", count + 2, e.getMessage());
                    continue; // Bỏ qua dòng có lỗi số
                }
            }
            entityManager.flush();
            logger.info("Completed processing {} records", count);
        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading CSV file: {}", e.getMessage());
        }
    }
}
