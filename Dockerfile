# Sử dụng image Java 17 (hoặc phiên bản phù hợp với dự án của bạn)
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file JAR từ target vào container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]