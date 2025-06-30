# Exam Score Management Application

This is a Spring Boot-based application designed to manage exam scores by loading data from a large CSV file (`diem_thi_thpt_2024.csv`) into an H2 in-memory database and providing a web interface to view the data. The application leverages Thymeleaf for templating, supports batch processing for large datasets.
- **With the version we deployed the website, there were only about 5000 records because I used the free version of Render, so it was limited, but the local version worked fine.**

<p align="center">
  <img src="https://raw.githubusercontent.com/nguyenvankhanh2010/intern_test/main/anh1.png" alt="Application Interface" title="Main Interface of Exam Score Management">
</p>

## Prerequisites
- **Java 17**: Check with `java -version`.
- **Maven 3.6+**: Check with `mvn -version`.
- **Git**: For cloning the repository.

## Installation and Running Locally

### 1. Clone the Repository
```bash
git clone https://github.com/nguyenvankhanh2010/intern_test.git
cd intern_test
```

### 2. Build the Project
```bash
mvn clean package -DskipTests
```
You can also go to DemoApplication and run

### 3. Run the Application
```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
Open your browser and go to http://localhost:8080 to see the application.
## Features
- Search by registration number.
<p align="center">
  <img src="https://raw.githubusercontent.com/nguyenvankhanh2010/intern_test/main/anh1.png" alt="Main Interface" title="Main Interface of Exam Score Management">
</p>

- Top 10 students in block A.
<p align="center">
  <img src="https://raw.githubusercontent.com/nguyenvankhanh2010/intern_test/main/anh2.png" alt="Search Feature" title="Search by Registration Number">
</p>

- Statistics of the number of students with scores.
<p align="center">
  <img src="https://raw.githubusercontent.com/nguyenvankhanh2010/intern_test/main/anh3.png" alt="Statistics" title="Statistics of Scores">
</p>
