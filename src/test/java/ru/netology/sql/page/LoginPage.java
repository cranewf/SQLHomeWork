package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id='login'] input");
    private SelenideElement password = $("[data-test-id='password'] input");
    private SelenideElement buttonLogin = $("[data-test-id='action-login']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage login(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        buttonLogin.click();
        return new VerificationPage();
    }

    public void verifyErrorNotification(String expectedText) {
        errorNotification.shouldBe(Condition.visible).shouldHave(Condition.text(expectedText));
    }

}
