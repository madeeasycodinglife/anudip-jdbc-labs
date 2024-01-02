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


/**
 * 08:48:25.553 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: 35a32e9611
 * 08:48:25.562 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Sophia
 * 08:48:25.563 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Clark
 * 08:48:25.563 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1996-12-18
 * 08:48:25.563 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Female
 * 08:48:25.563 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: sophia.c@email.com
 * 08:48:25.563 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 444-555-6666
 * 08:48:25.563 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.564 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: 3a64071133
 * 08:48:25.565 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Daniel
 * 08:48:25.565 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: White
 * 08:48:25.565 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1999-02-07
 * 08:48:25.565 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Male
 * 08:48:25.565 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: daniel.w@email.com
 * 08:48:25.565 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 777-888-9999
 * 08:48:25.565 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.567 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: 5d5bc002cc
 * 08:48:25.567 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Emma
 * 08:48:25.567 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Brown
 * 08:48:25.567 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1997-05-03
 * 08:48:25.568 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Female
 * 08:48:25.568 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: emma.brown@email.com
 * 08:48:25.568 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 789-321-6540
 * 08:48:25.568 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.568 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: 7dae3677e0
 * 08:48:25.569 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Mia
 * 08:48:25.569 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Wilson
 * 08:48:25.569 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1993-10-05
 * 08:48:25.569 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Female
 * 08:48:25.569 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: mia.w@email.com
 * 08:48:25.569 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 456-789-0123
 * 08:48:25.569 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.570 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: 7e49cdac77
 * 08:48:25.570 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Alice
 * 08:48:25.570 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Johnson
 * 08:48:25.571 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1998-07-22
 * 08:48:25.571 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Female
 * 08:48:25.571 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: alice.j@email.com
 * 08:48:25.571 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 987-654-3210
 * 08:48:25.571 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.571 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: 8559db4b49
 * 08:48:25.572 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Ethan
 * 08:48:25.572 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Davis
 * 08:48:25.572 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1998-01-12
 * 08:48:25.572 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Male
 * 08:48:25.573 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: ethan.d@email.com
 * 08:48:25.573 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 123-789-4560
 * 08:48:25.573 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.573 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: 8cf5cc10df
 * 08:48:25.574 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Bob
 * 08:48:25.574 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Smith
 * 08:48:25.574 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1993-11-10
 * 08:48:25.574 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Male
 * 08:48:25.574 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: bob.smith@email.com
 * 08:48:25.574 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 555-123-4567
 * 08:48:25.574 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.575 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: a621947367
 * 08:48:25.575 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Ava
 * 08:48:25.575 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Moore
 * 08:48:25.575 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1991-04-25
 * 08:48:25.575 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Female
 * 08:48:25.575 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: ava.m@email.com
 * 08:48:25.575 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 999-000-1111
 * 08:48:25.576 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.576 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: b2b4108e91
 * 08:48:25.577 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: John
 * 08:48:25.577 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Doe
 * 08:48:25.577 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1995-03-15
 * 08:48:25.577 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Male
 * 08:48:25.577 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: john.doe@email.com
 * 08:48:25.577 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 123-456-7890
 * 08:48:25.577 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.578 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: b4b591346f
 * 08:48:25.578 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: James
 * 08:48:25.578 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Miller
 * 08:48:25.578 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1990-08-31
 * 08:48:25.578 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Male
 * 08:48:25.579 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: james.m@email.com
 * 08:48:25.579 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 666-777-8888
 * 08:48:25.579 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.579 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: db66371fb7
 * 08:48:25.579 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Michael
 * 08:48:25.580 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Jones
 * 08:48:25.580 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1994-09-28
 * 08:48:25.580 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Male
 * 08:48:25.580 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: michael.j@email.com
 * 08:48:25.580 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 111-222-3333
 * 08:48:25.580 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Student ID: ed5070a7f8
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- First Name: Olivia
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Last Name: Taylor
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Date of Birth: 1992-06-14
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Gender: Female
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Email: olivia.t@email.com
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- Phone: 222-333-4444
 * 08:48:25.581 [main] INFO com.madeeasy.week10.session5.JDBCConnectionAndRetrievingRecords -- ---------------------------
 */