package com.example.test.demo.Repository;

import com.example.test.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByRegistrationNumber(String registrationNumber);

    @Query("SELECT s FROM Student s ORDER BY (s.math + s.physics + s.chemistry) DESC")
    List<Student> findTop10ByGroupA(Pageable pageable);
}