package com.lazaretov.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    public static final String REPOSITORY = "eroshenkoam/allure-example";
    public static final int ISSUE = 80;

    @BeforeAll
    static void start() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @DisplayName("Поиск Issue по номеру")
    public void lambdaStepTest() {
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие записи с номер #" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @Feature("Вкладка Issue")
    @Story("Поиск нужного Issue")
    @Owner("lazaretov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Testing site", url = "https://github.com/eroshenkoam/allure-example/issues")
    @DisplayName("Поиск Issue по номеру с аннотациями")
    public void annotatedStepTest() {
        WebSteps steps = new WebSteps();
        steps.openMainPaige();
        steps.searchRepository(REPOSITORY);
        steps.clickRepoLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueNumber(ISSUE);
    }
}

