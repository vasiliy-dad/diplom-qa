package ru.netology.tour.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static Faker fakerEn = new Faker(new Locale("en"));
    public static Faker fakerRu = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static String getNumberCardApproved() {
        return "4444 4444 4444 4441";
    }

    public static String getNumberCardDeclined() {
        return "4444 4444 4444 4442";
    }

    public static String getMonthFaker(int plusMonth) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateMinusMonth(int minusMonth, String formatPattern) {
        return LocalDate.now().minusMonths(minusMonth).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String getYearFaker(int plusYear) {
        return LocalDate.now().plusYears(plusYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateDateMinusYears(int minusYears, String formatPattern) {
        return LocalDate.now().minusYears(minusYears).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String generateDatePlusYears(int plusYears, String formatPattern) {
        return LocalDate.now().plusYears(plusYears).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String getOwnerFakerEng() {
        return fakerEn.name().fullName().toUpperCase();
    }

    public static String getInvalidOwnerFakerRu() {
        return fakerRu.name().firstName().toUpperCase() + " " + fakerRu.name().lastName().toUpperCase();
    }

    public static String getInvalidOwnerFullName() {
        return fakerEn.name().firstName().toUpperCase() + " " + fakerEn.name().firstName().toUpperCase() + " " + fakerEn.name().lastName().toUpperCase();
    }

    public static String getValidOwnerHyphenated() {
        return fakerEn.name().firstName().toUpperCase() + "-" + fakerEn.name().firstName().toUpperCase() + " " + fakerEn.name().lastName().toUpperCase();
    }

    public static String getOneLetter() {
        return fakerEn.name().name().substring(0, 1).toUpperCase();
    }

    public static String getTwoLetter() {
        return fakerEn.name().name().substring(0, 2).toLowerCase();
    }

    public static String getThreeLetter() {
        return fakerEn.name().name().substring(0, 3).toLowerCase();
    }

    public static String getInvalidRandomNumber() {
        return String.valueOf(fakerEn.number().randomNumber());
    }

    public static String getCVCFaker() {
        return fakerEn.numerify("###");
    }

    //Карта со статусом APPROVED
    public static DataCards getCardApproved() {
        return new DataCards(getNumberCardApproved(), "08", "25", "Ivan Petrov", "999");
    }

    //Карта со статусом DECLINED
    public static DataCards getCardDeclined() {
        return new DataCards(getNumberCardDeclined(), "08", "25", "Petr Smirnov", "123");
    }

    //Пустые поля
    public static DataCards getAllFieldsEmpty() {

        return new DataCards("", "", "", "", "");
    }

    //Поле "Номер карты"
    //Пустое поле
    public static DataCards getFieldCardNumberEmpty() {
        return new DataCards("", getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением меньше 16 цифр
    public static DataCards getFieldCardNumber15Digits() {
        return new DataCards("4444 4444 4444 444", getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением больше 16 цифр
    public static DataCards getFieldCardNumber17Digits() {
        return new DataCards("4444 4444 4444 4441 1", getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле буквами
    public static DataCards getFieldCardNumberLetters() {
        return new DataCards("фффф фффф ыыыы ыыыы", getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле спецсимволами
    public static DataCards getFieldCardNumberSpecialCharacters() {
        return new DataCards("*? |,.; :'! #%^&", getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле нулевыми значениями
    public static DataCards getFieldCardNumberZero() {
        return new DataCards("0000 0000 0000 0000", getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением невалидной карты
    public static DataCards getFieldCardNumberNotValid() {
        return new DataCards("1111 1111 1111 1111", getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Поле "Месяц"
    //Пустое поле
    public static DataCards getFieldMonthEmpty() {
        return new DataCards(getNumberCardApproved(), "", getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением меньше 2 цифр
    public static DataCards getFieldMonthOne() {
        return new DataCards(getNumberCardApproved(), "1", getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением больше 2 цифр
    public static DataCards getFieldMonthMoreThanTwo() {
        return new DataCards(getNumberCardApproved(), "122", getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле буквами
    public static DataCards getFieldMonthLetters() {
        return new DataCards(getNumberCardApproved(), getTwoLetter(), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле спецсимволами
    public static DataCards getFieldMonthSpecialCharacters() {
        return new DataCards(getNumberCardApproved(), "%%", getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле несуществующим месяцем
    public static DataCards getFieldMonthDoesNotExist() {
        return new DataCards(getNumberCardApproved(), "13", getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле прошлым месяцем текущего года
    public static DataCards getFieldMonthPreviousThisYear() {
        return new DataCards(getNumberCardApproved(), generateMinusMonth(1, "MM"), "25", getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле нулевым значением
    public static DataCards getFieldMonthZero() {
        return new DataCards(getNumberCardApproved(), "00", getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Поле "Год"
    //Пустое поле
    public static DataCards getFieldYearEmpty() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), "", getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением меньше 2 цифр
    public static DataCards getOneDigitInTheYearField() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), "2", getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением больше 2 цифр
    public static DataCards getDigitInTheYearFieldMoreThanTwo() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), "255", getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле буквами
    public static DataCards getInTheYearFieldLetters() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getTwoLetter(), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле спецсимволами
    public static DataCards getDigitInTheYearFieldSpecialCharacters() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), "№;", getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле нулевым значением
    public static DataCards getInTheYearFieldZero() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), "00", getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле значением предыдущего года
    public static DataCards getYearBeforeCurrent() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), generateDateMinusYears(1, "yy"), getOwnerFakerEng(), getCVCFaker());
    }

    //Поле "Владелец"
    //Пустое поле
    public static DataCards getFieldOwnerEmpty() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), "", getCVCFaker());
    }

    //Заполнить поле 1 буквой
    public static DataCards getFieldOwnerOneLetter() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getOneLetter(), getCVCFaker());
    }

    //Заполнить поле значением через пробел, состоящим из ФИО
    public static DataCards getFieldOwnerFullName() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getInvalidOwnerFullName(), getCVCFaker());
    }

    //Заполнить поле значением через пробел, состоящим из имени через дефис
    public static DataCards getFieldOwnerNameSeparatedHyphen() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getValidOwnerHyphenated(), getCVCFaker());
    }

    //Заполнить поле значением на кириллице
    public static DataCards getFieldOwnerInCyrillic() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getInvalidOwnerFakerRu(), getCVCFaker());
    }

    //Заполнить поле цифрами
    public static DataCards getFieldOwnerInNumbers() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getInvalidRandomNumber(), getCVCFaker());
    }

    //Заполнить поле спецсимволами
    public static DataCards getFieldOwnerSpecialCharacter() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), "%:;;№ ", getCVCFaker());
    }

    //Поле "CVC/CVV"
    //Пустое поле
    public static DataCards getFieldCVCEmpty() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), "");
    }

    //Заполнить поле значением меньше 3 цифр
    public static DataCards getCVCFieldLessThan3Digits() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), "9");
    }

    //Заполнить поле значением больше 3 цифр
    public static DataCards getCVCFieldMoreThan3Digits() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getCVCFaker());
    }

    //Заполнить поле буквами
    public static DataCards getCVCFieldLetters() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), getThreeLetter());
    }

    //Заполнить поле спецсимволами
    public static DataCards getCVCFieldSpecialCharacters() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), "*&%");
    }

    //Заполнить поле нулевым значением
    public static DataCards getCVCFieldZero() {
        return new DataCards(getNumberCardApproved(), getMonthFaker(0), getYearFaker(0), getOwnerFakerEng(), "000");
    }
}
