package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
    public HashMap<Integer,String> getWeeks(String subjectName) {
        //
        subjectName = subjectName.trim();
        HashMap<Integer,String> weeksMap = new HashMap<>();

        try {
            preparedStatement = connection.prepareStatement("""
                    select id,week_start,week_end from teachingWeeks
                    where subjects = ?
                    order by week_start""");

            preparedStatement.setString(1, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                int weekID = resultSet.getInt(1);
                LocalDate weekStart = resultSet.getDate(2).toLocalDate();
                LocalDate weekEnd = resultSet.getDate(3).toLocalDate();

                //
                String dayStart = String.valueOf(weekStart.getDayOfMonth());
                String monthStart = weekStart.getMonth().toString().substring(0, 3);
                String dayEnd = String.valueOf(weekEnd.getDayOfMonth());
                String monthEnd = weekEnd.getMonth().toString().substring(0, 3);

                //
                String week = monthStart + " "+dayStart+" -> "+ monthEnd+" "+dayEnd;

                weeksMap.put(weekID,week);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weeksMap;
    }

    //
    public List<Integer> getWeekID(String subjectName){
        //
        subjectName = subjectName.trim();
        List<Integer> weekIDs = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("""
                    select id from teachingWeeks
                    where subjects = ?
                    order by id""");

            preparedStatement.setString(1, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                int weekID = resultSet.getInt(1);

                weekIDs.add(weekID);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weekIDs;
    }






    //
    public HashMap<String,String> getCour(int weekID) {
        //
        HashMap<String,String> cours = new HashMap<>();

        try {
            preparedStatement = connection.prepareStatement("""
                    select cour_link,cour_name from subjectCour
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                String courLink = resultSet.getString(1);
                String courName = resultSet.getString(2);

                cours.put(courLink,courName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cours;
    }

    //
    public HashMap<Integer,HashMap<String,String>> getCours(List<Integer> weekIDs) {
        //
        HashMap<Integer,HashMap<String,String>> cours = new HashMap<>();
        HashMap<String,String> cour ;

        //
        for (Integer weekID : weekIDs) {
            //
            cour = this.getCour(weekID);
            cours.put(weekID,cour);

        }

        return cours;
    }


}