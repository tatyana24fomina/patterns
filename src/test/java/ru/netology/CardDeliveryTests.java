package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTests {

    @Test
    void shouldCheck_Card_delivery() {
        DataGenerator.UserInfo userInfo = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);
        open("http://localhost:9999/");

        $("[data-test-id = 'city'] input").sendKeys(userInfo.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = 'date'] input").sendKeys(date);
        $("[data-test-id = 'name'] input").sendKeys(userInfo.getName());
        $("[data-test-id = 'phone'] input").sendKeys(userInfo.getPhone());
        $("[data-test-id = 'agreement'] .checkbox__box").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id = 'success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

        date = DataGenerator.generateDate(7);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = 'date'] input").sendKeys(date);
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id = 'replan-notification'] .notification__title")
                .shouldHave(Condition.text("Необходимо подтверждение"))
                .shouldBe(Condition.visible);
        $$("button").find(exactText("Перепланировать")).click();

        $("[data-test-id = 'success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }
}
