package ru.netology.sql.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.SQLHelper;
import ru.netology.sql.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.sql.data.SQLHelper.*;

public class DeadlineTest {

    @AfterEach
    void cleanCode() {
        cleanAuthCode();
    }

    @AfterAll
    static void cleanBase() {
        cleanDataBase();
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    public void positiveLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.login(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void invalidUser() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.generateRandomUser();
        loginPage.login(authInfo);
        loginPage.verifyErrorNotification("Ошибка! " + "Неверно указан логин или пароль");
    }

    @Test
    public void invalidVerificationCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.login(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.validVerify(verificationCode);
        verificationPage.verifyErrorNotification("Ошибка! " + "Неверно указан код! Попробуйте ещё раз.");
    }
}
