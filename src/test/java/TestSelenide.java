import com.codeborne.selenide.Condition;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestSelenide {

    private String generateDate(int addDeys, String pattern){
        return LocalDate.now().plusDays(addDeys).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void cardTest() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Сыктывкар");
        String searchDate=generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(searchDate);
        $("[data-test-id='name'] input").setValue("Шварнегер Арнольд Юсуфович");
        $("[data-test-id='phone'] input").setValue("+79032736084");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
        .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на "+searchDate));
    }

}
