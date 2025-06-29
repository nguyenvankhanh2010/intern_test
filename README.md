# Exam Score Management Application

This is a Spring Boot-based application designed to manage exam scores by loading data from a large CSV file (`diem_thi_thpt_2024.csv`) into an H2 in-memory database and providing a web interface to view the data. The application leverages Thymeleaf for templating, supports batch processing for large datasets, and can be extended to use external storage solutions like AWS S3, Google Cloud Storage, or DigitalOcean Spaces.

## Prerequisites
- **Java 17**: Ensure JDK 17 is installed (`java -version` to check).
- **Maven 3.6+**: Build tool for managing dependencies (`mvn -version` to check).
- **Git**: For version control and cloning the repository.

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/nguyenvankhanh2010/intern_test.git
