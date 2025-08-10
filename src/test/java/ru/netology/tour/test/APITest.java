package ru.netology.tour.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("1. Статус валидной дебетовой карты в банковском сервисе payment")
    void shouldPostRequestDebitCardOnPaymentServiceWithStatusApproved() {
        given()
                .baseUri("http://localhost:9999")
                .body("{\"number\": \"4444 4444 4444 4441\"}")
                .contentType("application/json; charset=utf-8")
                // Выполняемые действия
                .when()
                .post("/payment")
                // Проверки
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"))
        ;
    }

    @Test
    @DisplayName("2. Статус отклоненной дебетовой карты в банковском сервисе payment")
    void shouldPostRequestDebitCardOnPaymentServiceWithStatusDeclined() {
        given()
                .baseUri("http://localhost:9999")
                .body("{\"number\": \"4444 4444 4444 4442\"}")
                .contentType("application/json; charset=utf-8")
                // Выполняемые действия
                .when()
                .post("/payment")
                // Проверки
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"))
        ;
    }

    @Test
    @DisplayName("3. Статус валидной дебетовой карты в банковском сервисе credit")
    void shouldPostRequestDebitCardOnCreditServiceWithStatusApproved() {
        given()
                .baseUri("http://localhost:9999")
                .body("{\"number\": \"4444 4444 4444 4441\"}")
                .contentType("application/json; charset=utf-8")
                // Выполняемые действия
                .when()
                .post("/credit")
                // Проверки
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"))
        ;
    }

    @Test
    @DisplayName("4. Статус отклоненной дебетовой карты в банковском сервисе credit")
    void shouldPostRequestDebitCardOnCreditServiceWithStatusDeclined() {
        given()
                .baseUri("http://localhost:9999")
                .body("{\"number\": \"4444 4444 4444 4442\"}")
                .contentType("application/json; charset=utf-8")
                // Выполняемые действия
                .when()
                .post("/credit")
                // Проверки
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"))
        ;
    }
}
