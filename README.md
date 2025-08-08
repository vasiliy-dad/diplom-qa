# Дипломный проект по профессии «Тестировщик»

## Инструкция по запуску окружения и автотестов

1. Запустить Docker Desktop
2. Создать новый проект в IntelliJ IDEA с настройками: JDK: Java (corretto-11), Build system: Gradle, Gradle DSL: Groovy
3. Клонировать репозиторий с GitHub, набрав в окне терминала команду:
  ```
    git clone https://github.com/vasiliy-dad/diplom-qa.git
```
4. Для запуска контейнеров с СУБД MySQL, PostgreSQL и эмулятором банковских сервисов, которые могут принимать запросы в нужном формате и генерировать предопределённые ответы для заданного набора карт, в новом окне терминала ввести команду:
  ```
    docker-compose up
```
5. В новом окне терминала запустить архив Java, содержащий веб-приложение по приобретению тура:
* С использованием СУБД MySQL
```
    java "-Dspring.datasource.url=jdbc:mysql://localhost:3300/app" -jar ./artifacts/aqa-shop.jar 
```
* С использованием СУБД PostgreSQL
```
    java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar 
```
6. В новом окне терминала запустить выполнение тестирования командой:
```
    ./gradlew clean test --info 
```
7. Сформировать отчет о проведенном тестировании, выполнив в терминале команду:
```
    ./gradlew allureServe 
```
*отчет откроется в браузере автоматически
