package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful replan meeting")
    void shouldSuccessfulPlanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);
        var daysForSecondMeeting = 5;
        var secondMeetingDate = DataGenerator.generateDate(daysForSecondMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='success-notification'] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate)).shouldBe(Condition.visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible);
        $$("[data-test-id='replan-notification'] button").findBy(Condition.text("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate)).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Checking if city is not indificate")
    void cityFieldIsNot() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Checking if data is not indificate")
    void dataFieldIsNot() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='date'] .input__sub").shouldBe(Condition.text("Неверно введена дата"));
    }

    @Test
    @DisplayName("Checking if name is not enter")
    void nameFieldIsNot() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Checking if phone number is not enter")
    void pnoneFieldIsNot() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Checking if checkbox is not mark")
    void checkBoxIsNot() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='agreement'] .checkbox__text")
                .shouldBe(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    @DisplayName("Checking if city is not region centre")
    void invalidCity() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        $("[data-test-id='city'] input").setValue("Альметьевск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldBe(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    @DisplayName("Checking if data is not exist (55.77.9999)")
    void invalidDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue("55.77.9999");
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='date'] .input__sub").shouldBe(Condition.text("Неверно введена дата"));
    }

    @Test
    @DisplayName("Checking if city have latin letters")
    void invalidName() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue("Lenar Khafizov");
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldBe(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    @DisplayName("Checking if the current date (01.06.2025) is specified")
    void invalidData() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue("01.06.2025");

        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(Condition.text("Запланировать")).click();
        $("[data-test-id='date'] .input__sub").
                shouldBe(Condition.text("Заказ на выбранную дату невозможен"));
    }
}
