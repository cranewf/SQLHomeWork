package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public void verificationPageVisible() {
        codeField.shouldBe(Condition.visible);
    }

    public void validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }

    public DashboardPage dashboardPageVerify(String verificationCode) {
        validVerify(verificationCode);
        return new DashboardPage();
    }

    public void verifyErrorNotification(String expectedText) {
        errorNotification.shouldBe(Condition.visible).shouldHave(Condition.text(expectedText));
    }
}
