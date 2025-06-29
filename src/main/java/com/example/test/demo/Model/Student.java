package com.example.test.demo.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "math")
    private Float math;

    @Column(name = "physics")
    private Float physics;

    @Column(name = "chemistry")
    private Float chemistry;

    @Column(name = "history")
    private Float history;

    @Column(name = "literature")
    private Float literature;

    @Column(name = "geography")
    private Float geography;

    @Column(name = "english")
    private Float english;

    @Column(name = "biology")
    private Float biology;

    @Column(name = "civiceducation")
    private Float civiceducation;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Float getMath() {
        return math;
    }

    public void setMath(Float math) {
        this.math = math;
    }

    public Float getPhysics() {
        return physics;
    }

    public void setPhysics(Float physics) {
        this.physics = physics;
    }

    public Float getChemistry() {
        return chemistry;
    }

    public void setChemistry(Float chemistry) {
        this.chemistry = chemistry;
    }

    public Float getHistory() {
        return history;
    }

    public void setHistory(Float history) {
        this.history = history;
    }

    public Float getLiterature() {
        return literature;
    }

    public void setLiterature(Float literature) {
        this.literature = literature;
    }

    public Float getGeography() {
        return geography;
    }

    public void setGeography(Float geography) {
        this.geography = geography;
    }

    public Float getCiviceducation() {
        return civiceducation;
    }

    public void setCiviceducation(Float civiceducation) {
        this.civiceducation = civiceducation;
    }

    public Float getEnglish() {
        return english;
    }

    public void setEnglish(Float english) {
        this.english = english;
    }

    public Float getBiology() {
        return biology;
    }

    public void setBiology(Float biology) {
        this.biology = biology;
    }
}