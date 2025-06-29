package com.example.test.demo.Database;


import com.example.test.demo.Model.Student;
import com.example.test.demo.Repository.StudentRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class CsvDataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        InputStream is = 
            this.getClass()
                .getClassLoader()
                .getResourceAsStream("diem_thi_thpt_2024.csv");
        if (is == null) {
            throw new FileNotFoundException(
                "Không tìm thấy diem_thi_thpt_2024.csv trên classpath");
        }

        try (CSVReader csvReader = 
               new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            csvReader.readNext(); // skip header
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                Student student = new Student();
                student.setRegistrationNumber(record[0]);
                student.setMath(parseFloatOrNull(record[1]));
                student.setLiterature(parseFloatOrNull(record[2]));
                student.setEnglish(parseFloatOrNull(record[3]));
                student.setPhysics(parseFloatOrNull(record[4]));
                student.setChemistry(parseFloatOrNull(record[5]));
                student.setBiology(parseFloatOrNull(record[6]));
                student.setHistory(parseFloatOrNull(record[7]));
                student.setGeography(parseFloatOrNull(record[8]));
                student.setCiviceducation(parseFloatOrNull(record[9]));
                studentRepository.save(student);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private Float parseFloatOrNull(String s) {
        return (s == null || s.isBlank())
            ? null
            : Float.parseFloat(s);
    }
}
