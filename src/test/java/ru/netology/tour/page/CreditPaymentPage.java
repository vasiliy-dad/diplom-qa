package ru.netology.tour.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.tour.data.DataCards;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPaymentPage {
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardNumberFieldError = $$(".input__inner").find(Condition.text("Номер карты")).$(".input__sub");
    private final SelenideElement cardMonthField = $("input[placeholder='08']");
    private final SelenideElement monthFieldError = $$(".input__inner").find(Condition.text("Месяц")).$(".input__sub");
    private final SelenideElement cardYearField = $("input[placeholder='22']");
    private final SelenideElement yearFieldError = $$(".input__inner").find(Condition.text("Год")).$(".input__sub");
    private final SelenideElement cardOwnerField = $$(".input__inner").find(Condition.text("Владелец")).$(".input__control");
    private final SelenideElement ownerFieldError = $$(".input__inner").find(Condition.text("Владелец")).$(".input__sub");
    private final SelenideElement cardCVCField = $("input[placeholder='999']");
    private final SelenideElement cvcFieldError = $$(".input__inner").find(Condition.text("CVC/CVV")).$(".input__sub");
    private final SelenideElement continueButton = $$(".button__content").find(Condition.text("Продолжить"));

    private final SelenideElement notificationSuccess = $(".notification_status_ok");
    private final SelenideElement notificationError = $(".notification_status_error");

    public void debitCardData(DataCards info) {
        cardNumberField.setValue(info.getNumber());
        cardMonthField.setValue(info.getMonth());
        cardYearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getOwner());
        cardCVCField.setValue(info.getCvc());
        continueButton.click();
    }

    public void allFieldsWithErrors() {
        continueButton.shouldBe(visible).click();
        cardNumberFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
        monthFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
        yearFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
        ownerFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(10));
        cvcFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void notificationOk() {
        notificationSuccess.should(visible, Duration.ofSeconds(30));
        notificationSuccess.$("[class=notification__title]").should(text("Успешно")).shouldBe(visible, Duration.ofSeconds(20));
        notificationSuccess.$("[class=notification__content]").should(text("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(20));
    }

    public void notificationFailed() {
        notificationError.should(visible, Duration.ofSeconds(30));
        notificationError.$("[class=notification__title]").should(text("Ошибка")).shouldBe(visible, Duration.ofSeconds(20));
        notificationError.$("[class=notification__content]").should(text("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(20));
    }

    public void invalidFormatCardNumberField() {

        cardNumberFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidFormatMonthField() {
        monthFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidCardExpirationDateMonth() {
        monthFieldError.shouldHave(text("Неверно указан срок действия карты")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidFormatYearField() {
        yearFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidFormatCardExpired() {
        yearFieldError.shouldHave(text("Истёк срок действия карты")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidCardExpirationDateYear() {
        yearFieldError.shouldHave(text("Неверно указан срок действия карты")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidFormatRequiredOwnerField() {
        ownerFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidFormatOwnerField() {
        ownerFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidFormatOwnerFieldValueMustContainMoreThanOneLetter() {
        ownerFieldError.shouldHave(text("Значение поля должно содержать больше одной буквы")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void invalidFormatCVCField() {
        cvcFieldError.shouldHave(text("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }
}
