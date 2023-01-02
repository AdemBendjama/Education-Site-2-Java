package model;

import java.sql.*;
import java.time.LocalDate;
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
    public List<Subject> getSubjects() {
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
    public List<Subject> getTeacherSubjects(String teacherEmail) {
        //
        teacherEmail = teacherEmail.trim();
        List<Subject> subjects = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("""
                    select * from subjects,users,teacherSubjects
                    where teacherSubjects.email = users.user_email
                    and   subjects.name = teacherSubjects.subjects
                    and teacherSubjects.email = ?""");

            preparedStatement.setString(1, teacherEmail);
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
    public List<String> getWeeks(String subjectName) {
        //
        subjectName = subjectName.trim();
        List<String> weeks = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("""
                    select week_start,week_end from teachingWeeks
                    where subjects = ?
                    order by week_start""");

            preparedStatement.setString(1, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                LocalDate weekStart = resultSet.getDate(1).toLocalDate();
                LocalDate weekEnd = resultSet.getDate(2).toLocalDate();

                //
                String dayStart = String.valueOf(weekStart.getDayOfMonth());
                String monthStart = weekStart.getMonth().toString().substring(0, 3);
                String dayEnd = String.valueOf(weekEnd.getDayOfMonth());
                String monthEnd = weekEnd.getMonth().toString().substring(0, 3);

                //
                String week = monthStart + " "+dayStart+" -> "+ monthEnd+" "+dayEnd;

                weeks.add(week);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weeks;
    }

}