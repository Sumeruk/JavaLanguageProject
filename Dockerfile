# Использование официального образа Java в качестве базового изображения
FROM tomcat:10.0

# Установка рабочего каталога в контейнере
WORKDIR /usr/local/tomcat

# Копирование файла WAR в директорию webapps Tomcat
COPY ./target/task1-16-1.0.war /usr/local/tomcat/webapps/

# Предоставление порта 8080 для доступа извне контейнера
EXPOSE 8080

# Запуск сервера Tomcat
CMD ["catalina.sh", "run"]