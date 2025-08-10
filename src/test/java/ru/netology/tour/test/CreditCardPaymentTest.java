package ru.netology.tour.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.tour.data.DataHelper;
import ru.netology.tour.data.SQLHelper;
import ru.netology.tour.page.CreditPaymentPage;
import ru.netology.tour.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardPaymentTest {
    MainPage mainPage;
    CreditPaymentPage creditPage;

    @BeforeEach
    void shouldOpenPage() {
        SQLHelper.cleanTables();
        mainPage = open("http://localhost:8080", MainPage.class);
        creditPage = mainPage.clickCreditCard();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    //Покупка тура через вкладку "Купить в кредит"
    @Test
    @DisplayName("1. Покупка тура по дебетовой карте со статусом APPROVED")
    public void shouldApprovedCard() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getCardApproved());
        creditPage.notificationOk();

        assertEquals("APPROVED", SQLHelper.getCreditStatusDB());
        assertEquals(1, SQLHelper.getOrderCount());
        assertEquals(1, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("2.Покупка тура при вводе данных карты со статусом DECLINED")
    public void shouldDeclinedCard() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getCardDeclined());
        creditPage.notificationFailed();

        assertEquals("DECLINED", SQLHelper.getCreditStatusDB());
        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("3.Покупка тура при отправке пустых значений в полях формы")
    public void shouldAllFieldsAreEmpty() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getAllFieldsEmpty());
        creditPage.allFieldsWithErrors();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    //Проверка валидации полей ввода данных по вкладке "Купить в кредит"

    //Поле "Номер карты"
    @Test
    @DisplayName("4.Поле номер карты пустое")
    public void shouldEmptyCardNumberField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCardNumberEmpty());
        creditPage.invalidFormatCardNumberField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("5.Поле номер карты заполнено значением меньше 16 цифр")
    public void should15DigitsInTheCardNumberField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCardNumber15Digits());
        creditPage.invalidFormatCardNumberField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("6.Поле номер карты заполнено значением больше 16 цифр")
    public void should17DigitInTheCardNumberField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCardNumber17Digits());
        creditPage.notificationOk();

        assertEquals(1, SQLHelper.getOrderCount());
        assertEquals(1, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("7.Поле номер карты заполнено буквами")
    public void shouldLettersDigitInTheCardNumberField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCardNumberLetters());
        creditPage.invalidFormatCardNumberField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("8.Поле номер карты заполнено спецсимволами")
    public void shouldSpecialCharactersDigitInTheCardNumberField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCardNumberSpecialCharacters());
        creditPage.invalidFormatCardNumberField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("9.Поле номер карты заполнено нулевым значением")
    public void shouldZeroDigitInTheCardNumberField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCardNumberZero());
        creditPage.notificationFailed();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("10.Поле номер карты заполнено значением невалидной карты")
    public void shouldCardNumberFieldDataCardNotValid() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCardNumberNotValid());
        creditPage.notificationFailed();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    //Поле "Месяц"
    @Test
    @DisplayName("11.Поле месяц пустое")
    public void shouldEmptyMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthEmpty());
        creditPage.invalidFormatMonthField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("12.Поле месяц заполнено значением меньше 2 цифр")
    public void should1DigitInTheMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthOne());
        creditPage.invalidFormatMonthField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("13.Поле месяц заполнено значением больше 2 цифр")
    public void shouldThanTwoDigitInTheMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthMoreThanTwo());
        creditPage.notificationOk();

        assertEquals(1, SQLHelper.getOrderCount());
        assertEquals(1, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("14.Поле месяц заполнено буквами")
    public void shouldLettersDigitInTheMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthLetters());
        creditPage.invalidFormatMonthField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("15.Поле месяц заполнено спецсимволами")
    public void shouldSpecialCharactersDigitInTheMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthSpecialCharacters());
        creditPage.invalidFormatMonthField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("16.Поле месяц заполнено несуществующим месяцем")
    public void shouldNotExistMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthDoesNotExist());
        creditPage.invalidCardExpirationDateMonth();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("17.Поле месяц заполнено прошлым месяцем текущего года")
    public void shouldPreviousThisYearMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthPreviousThisYear());
        creditPage.invalidCardExpirationDateMonth();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("18.Поле месяц заполнено нулевым значением")
    public void shouldZeroTheMonthField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldMonthZero());
        creditPage.invalidCardExpirationDateMonth();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    //Поле "Год"
    @Test
    @DisplayName("19.Поле год пустое")
    public void shouldEmptyYearField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldYearEmpty());
        creditPage.invalidFormatYearField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("20.Поле год заполнено значением меньше 2 цифр")
    public void should1DigitInTheYearField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getOneDigitInTheYearField());
        creditPage.invalidFormatYearField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("21.Поле год заполнено значением больше 2 цифр")
    public void shouldThanTwoDigitInTheYearField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getDigitInTheYearFieldMoreThanTwo());
        creditPage.notificationOk();

        assertEquals(1, SQLHelper.getOrderCount());
        assertEquals(1, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("22.Поле год заполнено буквами")
    public void shouldLetterDigitInTheYearField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getInTheYearFieldLetters());
        creditPage.invalidFormatYearField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("23.Поле год заполнено спецсимволами")
    public void shouldSpecialCharactersDigitInTheYearField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getDigitInTheYearFieldSpecialCharacters());
        creditPage.invalidFormatYearField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("24.Поле год заполнено нулевым значением")
    public void shouldZeroInTheYearField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getInTheYearFieldZero());
        creditPage.invalidFormatCardExpired();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("25.Поле год заполнено значением предыдущего года")
    public void shouldYearPrecedingTheCurrentOne() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getYearBeforeCurrent());
        creditPage.invalidFormatCardExpired();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    //Поле "Владелец"
    @Test
    @DisplayName("26.Поле владелец пустое")
    public void shouldEmptyOwnerField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldOwnerEmpty());
        creditPage.invalidFormatRequiredOwnerField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("27.Поле владелец заполнено 1 буквой")
    public void shouldOneLetterOwnerField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldOwnerOneLetter());
        creditPage.invalidFormatOwnerFieldValueMustContainMoreThanOneLetter();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("28.Поле владелец заполнено значением через пробел, состоящим из двойного имени")
    public void shouldFullNameInTheOwnerField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldOwnerFullName());
        creditPage.notificationOk();

        assertEquals(1, SQLHelper.getOrderCount());
        assertEquals(1, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("29.Поле владелец заполнено значением через пробел, состоящим из имени через дефис")
    public void shouldNameSeparatedHyphenInTheOwnerField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldOwnerNameSeparatedHyphen());
        creditPage.notificationOk();

        assertEquals(1, SQLHelper.getOrderCount());
        assertEquals(1, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("30.Поле владелец заполнено значением на кириллице")
    public void shouldInCyrillicOwnerField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldOwnerInCyrillic());
        creditPage.invalidFormatOwnerField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("31.Поле владелец заполнено цифрами")
    public void shouldNumbersInTheOwnerField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldOwnerInNumbers());
        creditPage.invalidFormatOwnerField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("32.Поле владелец заполнено спецсимволами")
    public void shouldSpecialCharacterOwnerField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldOwnerSpecialCharacter());
        creditPage.invalidFormatOwnerField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    //Поле "CVC/CVV"
    @Test
    @DisplayName("33.Поле CVC пустое")
    public void shouldEmptyCVCField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getFieldCVCEmpty());
        creditPage.invalidFormatCVCField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("34.Поле CVC заполнено значением меньше 3 цифр")
    public void shouldLessThan3DigitsCVCField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getCVCFieldLessThan3Digits());
        creditPage.invalidFormatCVCField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("35.Поле CVC заполнено значением больше 3 цифр")
    public void shouldMoreThan3DigitsCVCField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getCVCFieldMoreThan3Digits());
        creditPage.notificationOk();

        assertEquals(1, SQLHelper.getOrderCount());
        assertEquals(1, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("36.Поле CVC заполнено буквами")
    public void shouldLettersCVCField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getCVCFieldLetters());
        creditPage.invalidFormatCVCField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("37.Поле CVC заполнено спецсимволами")
    public void shouldSpecialCharactersCVCField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getCVCFieldSpecialCharacters());
        creditPage.invalidFormatCVCField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }

    @Test
    @DisplayName("38.Поле CVC заполнено нулевым значением")
    public void shouldZeroTheCVCField() {
        mainPage.clickCreditCard();
        creditPage.debitCardData(DataHelper.getCVCFieldZero());
        creditPage.invalidFormatCVCField();

        assertEquals(0, SQLHelper.getOrderCount());
        assertEquals(0, SQLHelper.getCreditCount());
    }
}
