package ru.netology.tour.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static void cleanTables() {
        var credit = "DELETE FROM credit_request_entity;";
        var order = "DELETE FROM order_entity;";
        var payment = "DELETE FROM payment_entity;";
        var runner = new QueryRunner();
        try (var connection = getConn();) {
            runner.update(connection, credit);
            runner.update(connection, order);
            runner.update(connection, payment);
        }
    }

    @SneakyThrows
    public static String getPaymentStatusDB() {
        var status = "SELECT status FROM payment_entity;";
        var runner = new QueryRunner();
        String payStatus;

        try (var conn = getConn();
        ) {
            payStatus = runner.query(conn, status, new ScalarHandler<>());
        }

        return payStatus;
    }

    @SneakyThrows
    public static String getCreditStatusDB() {
        var status = "SELECT status FROM credit_request_entity;";
        var runner = new QueryRunner();
        String creditStatus;

        try (var conn = getConn();
        ) {
            creditStatus = runner.query(conn, status, new ScalarHandler<>());
        }
        return creditStatus;
    }

    @SneakyThrows
    public static long getPaymentCount() {
        var count = "SELECT COUNT(id) as count FROM payment_entity;";
        var runner = new QueryRunner();
        long payCount;

        try (
                var conn = getConn();
        ) {
            payCount = runner.query(conn, count, new ScalarHandler<>());
        }
        return payCount;
    }

    @SneakyThrows
    public static long getCreditCount() {
        var count = "SELECT COUNT(id) as count FROM credit_request_entity;";
        var runner = new QueryRunner();
        long creditCount;

        try (var conn = getConn();
        ) {
            creditCount = runner.query(conn, count, new ScalarHandler<>());
        }
        return creditCount;
    }

    @SneakyThrows
    public static long getOrderCount() {
        var count = "SELECT COUNT(id) as count FROM order_entity;";
        var runner = new QueryRunner();
        long orderCount;

        try (var conn = getConn();
        ) {
            orderCount = runner.query(conn, count, new ScalarHandler<>());
        }
        return orderCount;
    }
}
