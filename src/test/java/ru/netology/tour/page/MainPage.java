package ru.netology.tour.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static SelenideElement mainPage = $("h2").shouldHave(Condition.exactText("Путешествие дня"))
            .shouldBe(Condition.visible);
    private static SelenideElement debitCardPayment = $(withText("Купить"));
    private static SelenideElement visibleDebitCardPaymentText = $(withText("Оплата по карте"));
    private static SelenideElement creditCardPayment = $(withText("Купить в кредит"));
    private static SelenideElement visibleCreditCardPaymentText = $(withText("Кредит по данным карты"));

    public CardPaymentPage clickDebitCard() {
        mainPage.shouldHave(text("Путешествие дня")).shouldBe(visible);
        debitCardPayment.shouldHave(text("Купить")).click();
        visibleDebitCardPaymentText.shouldHave(text("Оплата по карте")).shouldBe(visible);
        return new CardPaymentPage();
    }

    public CreditPaymentPage clickCreditCard() {
        mainPage.shouldHave(text("Путешествие дня")).shouldBe(visible);
        creditCardPayment.shouldHave(text("Купить в кредит")).click();
        visibleCreditCardPaymentText.shouldHave(text("Кредит по данным карты")).shouldBe(visible);
        return new CreditPaymentPage();
    }
}
