# Exam Score Management Application

This is a Spring Boot-based application designed to manage exam scores by loading data from a large CSV file (`diem_thi_thpt_2024.csv`) into an H2 in-memory database and providing a web interface to view the data. The application leverages Thymeleaf for templating, supports batch processing for large datasets.

<p align="center">
  <img src="https://raw.githubusercontent.com/nguyenvankhanh2010/intern_test/main/Screenshot%202025-06-29%20213030.png" alt="Application Interface" title="Main Interface of Exam Score Management">
</p>

## Features
- Load and process large CSV files containing exam scores into an H2 database.
- Display scores dynamically via a web interface using Thymeleaf templates.
- Optimize memory usage with batch processing (default 50 records per batch).
- Support for local development and deployment on cloud platforms (e.g., Render).
- Optional integration with external storage (AWS S3, Google Cloud Storage, DigitalOcean Spaces) for handling oversized files.

## Prerequisites
- **Java 17**: Ensure JDK 17 is installed (`java -version` to check).
- **Maven 3.6+**: Build tool for managing dependencies (`mvn -version` to check).
- **Git**: For version control and cloning the repository.
- **Optional Dependencies**: AWS SDK, Google Cloud Storage SDK, or DigitalOcean Spaces SDK for external storage integration.

## Installation
