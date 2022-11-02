package com.lazaretov.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPaige() {
        open("https://github.com/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(repo);
        $(".header-search-input").submit();
    }

    @Step("Переходим в репозиторий {repo}")
    public void clickRepoLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем вкладку Issues")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие записи с номер #{issue}")
    public void shouldSeeIssueNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }
}
