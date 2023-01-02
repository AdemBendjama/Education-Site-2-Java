package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SubjectManager {
    private final String url = "jdbc:mysql://localhost:3306/TQL_APP_BD";
    private final String root = "root";
    private final String password = "password";
    //
    private Connection connection;
    private PreparedStatement preparedStatement;

    //
    public SubjectManager() {
        //
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, root, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //
    public boolean testConnection() {
        //
        try {
            DriverManager.getConnection(url, root, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public List<Subject> getSubjects(){
        //
        List<Subject> subjects = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from subjects");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                Subject subject = new Subject(resultSet.getString(1), resultSet.getString(2));

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    //
    public List<Subject> getTeacherSubjects(String teacherEmail){
        //
        List<Subject> subjects = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("""
                                                                select * from subjects,users,teacherSubjects
                                                                where teacherSubjects.email = users.user_email
                                                                and   subjects.name = teacherSubjects.subjects
                                                                and teacherSubjects.email = ?""");

            preparedStatement.setString(1,teacherEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                Subject subject = new Subject(resultSet.getString(1), resultSet.getString(2));

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

}