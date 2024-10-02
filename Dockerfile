FROM maven:3.8.4-openjdk-11 AS builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл pom.xml и зависимости
COPY pom.xml .
COPY src ./src

# Скачиваем зависимости
RUN mvn dependency:go-offline

# Собираем проект
RUN mvn package

# Используем базовый образ OpenJDK для запуска приложения
FROM openjdk:11-jre-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR-файл из предыдущего этапа
COPY --from=builder /app/target/*.jar app.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]