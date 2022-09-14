package ru.gb.naumovets.infoFromLessons.lesson2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcDemo {
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {
        try {
            connect();
            readEx();
            setStudentsEx();
//            readExPreparedStatement();
//            stmtBatchEx();
//            createTableEx();
//            psBatchEx();
//            deleteEx();
//            insertEx();
//            readExFromStudents_1();
//            updateEx();
//            readExFromStudents_1();
//            dropTableEx();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Павел\\Desktop\\sqlite-tools-win32-x86-3380200\\sqlite-tools-win32-x86-3380200\\demobase.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readEx() throws SQLException {
        try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM students;")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString("name") + " " + resultSet.getInt(3));
            }
        }
    }

    private static void setStudentsEx() throws SQLException{
        List<Student> studentsList = new ArrayList<>();
        try(ResultSet resultSet = stmt.executeQuery("SELECT * FROM students;")){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");

                Student student = new Student(id, name, score);
                studentsList.add(student);
            }
        }
        System.out.println(studentsList);
    }

    //Подготовленный запрос (скомпилированная версия SQL - выражения)
    private static void readExPreparedStatement() throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM students WHERE id = ?;");
        ps.setInt(1,1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt(3));
        }
    }


    private static void readExFromStudents_1() throws SQLException{
        try(ResultSet resultSet = stmt.executeQuery("SELECT * FROM students_1")){
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString("name") + " " + resultSet.getInt(3));
            }
        }
    }

    private static void createTableEx() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students_1 (\n" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " name TEXT,\n" +
                " score INTEGER\n" +
                " );");
    }

    private static void dropTableEx() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS students_1;");
    }

    private static void deleteEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM students_1 WHERE name = 'Scot';");
    }

    private static void updateEx() throws SQLException {
        stmt.executeUpdate("UPDATE students_1 SET score = 200 WHERE name = 'Scot';");
    }
    public static void insertEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO students_1 (id, name, score) VALUES (1, 'Scot', 100);");
    }

    //Пакетное выполнение запросов (Batch) через PreparesStatement
    private static void psBatchEx() throws SQLException{
        try(PreparedStatement prepInsert = connection.prepareStatement("INSERT INTO students_1 (name, score) VALUES (?,?);")){
            for (int i = 0; i < 10; i++) {
                prepInsert.setString(1,"Scot" + i);
                prepInsert.setInt(2,i * 10 % 100);
                prepInsert.addBatch();
            }
            int[] result = prepInsert.executeBatch();
        }
    }
    //Пакетное выполнение запросов (Batch) через Statement
//    private static void stmtBatchEx(){
//        try{
//            for (int i = 10; i < 20; i++) {
//                stmt.addBatch(String.format("INSERT INTO students_1 (name, score) VALUES (%s, %d)", 'S' + i, i * 10 % 100));
//            }
//            int[] result = stmt.executeBatch();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

