## Arithmetic test

Тестовое задание Java:
Дано
Дан файл вида: 
data file
operand1;operand2;operation;result
operand1;operand2;operation;result
operand1;operand2;operation;result
operand1;operand2;operation;result
каждая строка описывает арифметическое действие. 
* operand1 и operand2 - операнды, целые числа
* operation - арифметическое действие + - / *
* result - результат операции operation над operand1 и operand2
В файле могут содержаться любые значения полей
Требуется
* Реализовать юнит (JUnit) тесты арифметических действий.
* Каждое действие должно выглядеть в отчете как отдельный тестовый сценарий
* Результатом выполнения должен быть Yandex.Allure отчет
* Операнды, операция и результат должны быть представлены в отчете как параметры теста
* Проект должен быть оформлен как артефакт Maven
* Исходный код проекта должен быть выложен на github или bitbucket

To run tests you need to install [maven][maven].

To generate Allure Report you should perform following steps:

```bash
$ mvn clean test
$ mvn site
```

To see a report, run `mvn jetty:run` and open `http://localhost:8080` in your browser

**NOTE: required maven version 3.1.1 or above**


