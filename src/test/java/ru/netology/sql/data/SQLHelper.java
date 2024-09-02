package ru.netology.sql.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private SQLHelper(){
    }

    public static final QueryRunner queryRunner = new QueryRunner();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getVerificationCode(){
        var codeSQL = "SELECT code FROM auth_code ORDER BY created DESC LIMIT 1";
        var connect = getConnection();
        return queryRunner.query(connect, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDataBase(){
        var connect = getConnection();
        queryRunner.execute(connect, "DELETE FROM auth_code");
        queryRunner.execute(connect, "DELETE FROM cards");
        queryRunner.execute(connect, "DELETE FROM users");
        queryRunner.execute(connect, "DELETE FROM card_transaction");
    }

    @SneakyThrows
    public static void cleanAuthCode(){
        var connect = getConnection();
        queryRunner.execute(connect, "DELETE FROM auth_code");
    }
}
