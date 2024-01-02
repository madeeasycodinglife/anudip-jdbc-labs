package com.madeeasy.week10.session5;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class JDBCConnectionAndRetrievingRecords {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            retrieveAndPrintStudentData(connection);
        } catch (SQLException e) {
            System.err.println("Error accessing the database: " + e.getMessage());
        }
    }


    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
            // Consider logging the exception or rethrowing a custom exception for better handling
            return null;  // Or handle the error in a way appropriate for your application
        }
    }


    private static void retrieveAndPrintStudentData(Connection connection) {
        String sqlQuery = "SELECT * FROM students";
        try (var prepareStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = prepareStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                printStudentData(studentId, firstName, lastName, dateOfBirth, gender, email, phone);

            }
        } catch (SQLException exception) {
            log.error("Exception occurred : {}", exception.getMessage());
        }
    }

    private static void printStudentData(String studentId, String firstName, String lastName,
                                         String dateOfBirth, String gender, String email, String phone) {
        log.info("Student ID: {}", studentId);
        log.info("First Name: {}", firstName);
        log.info("Last Name: {}", lastName);
        log.info("Date of Birth: {}", dateOfBirth);
        log.info("Gender: {}", gender);
        log.info("Email: {}", email);
        log.info("Phone: {}", phone);
        log.info("---------------------------");
    }

}
