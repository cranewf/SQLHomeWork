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
    LoginPage loginPage;

    @AfterAll
    static void cleanBase(){
        cleanDataBase();
    }

    @AfterEach
    void cleanCode(){
        cleanAuthCode();
    }

    @BeforeEach
    void setup(){
        open("http://localhost:9999");
    }

    @Test
    public void positiveLogin(){
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }
}
