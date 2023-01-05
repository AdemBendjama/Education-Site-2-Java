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
    public HashMap<Integer, String> getWeeks(String subjectName) {
        //
        subjectName = subjectName.trim();
        HashMap<Integer, String> weeksMap = new HashMap<>();

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
                String week = monthStart + " " + dayStart + " -> " + monthEnd + " " + dayEnd;

                weeksMap.put(weekID, week);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weeksMap;
    }

    //
    public int getWeekID(String subjectName, String weekStart, String weekEnd) {
        //
        subjectName = subjectName.trim();
        weekStart = weekStart.trim();
        weekEnd = weekEnd.trim();

        int weekID = 0;

        try {
            preparedStatement = connection.prepareStatement("""
                    select id from teachingWeeks
                    where subjects =?
                    AND week_start =?
                    AND week_end=?
                    LIMIT 1""");

            preparedStatement.setString(1, subjectName);
            preparedStatement.setString(2, weekStart);
            preparedStatement.setString(3, weekEnd);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            //
            weekID = resultSet.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weekID;
    }

    //
    public List<Integer> getWeekIDs(String subjectName) {
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

    public boolean checkWeek(String subjectName, String weekStart, String weekEnd) {
        //
        subjectName = subjectName.trim();
        weekStart = weekStart.trim();
        weekEnd = weekEnd.trim();

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Select * from teachingWeeks
                    where subjects = ?
                    AND week_start=?
                    AND week_end=?""");

            preparedStatement.setString(1, subjectName);
            preparedStatement.setString(2, weekStart);
            preparedStatement.setString(3, weekEnd);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public boolean checkDesc(int weekID) {
        //
        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Select * from subjectDesc
                    where teachingWeek = ?
                    LIMIT 1""");

            preparedStatement.setInt(1, weekID);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public boolean checkCour(String link) {
        //
        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Select * from subjectCour
                    where cour_link = ?
                    LIMIT 1""");

            preparedStatement.setString(1, link);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public boolean checkTD(String link) {
        //
        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Select * from subjectTD
                    where td_link = ?
                    LIMIT 1""");

            preparedStatement.setString(1, link);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public boolean checkTP(String link) {
        //
        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Select * from subjectTP
                    where tp_link = ?
                    LIMIT 1""");

            preparedStatement.setString(1, link);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public boolean addWeek(String subjectName, String weekStart, String weekEnd) {
        //
        subjectName = subjectName.trim();
        weekStart = weekStart.trim();
        weekEnd = weekEnd.trim();

        //
        if (checkWeek(subjectName, weekStart, weekEnd)) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Insert into teachingWeeks
                    (subjects,week_start,week_end)
                    Values (?,?,?)""");

            preparedStatement.setString(1, subjectName);
            preparedStatement.setString(2, weekStart);
            preparedStatement.setString(3, weekEnd);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public boolean deleteWeek(int weekID) {
        //
        if (weekID == 0) {
            return false;
        }

        //
        this.deleteCours(weekID);
        this.deleteTDs(weekID);
        this.deleteTPs(weekID);
        this.deleteDescs(weekID);


        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Delete from teachingWeeks
                    where id=?""");

            preparedStatement.setInt(1, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public boolean deleteCours(int weekID) {
        //
        if (weekID == 0) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Delete from subjectCour
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public boolean deleteTDs(int weekID) {
        //
        if (weekID == 0) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Delete from subjectTD
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public boolean deleteTPs(int weekID) {
        //
        if (weekID == 0) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Delete from subjectTP
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public boolean deleteDescs(int weekID) {
        //
        if (weekID == 0) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Delete from subjectDesc
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    //
    public boolean changeDesc(int weekID, String description) {
        //
        description = description.trim();

        if (description.isBlank()) {
            return false;
        }

        //
        if (!checkDesc(weekID)) {

            try {
                //
                preparedStatement = connection.prepareStatement("""
                        Insert into subjectDesc
                        (teachingWeek,descriptionInfo)
                        Values (?,?)""");

                preparedStatement.setInt(1, weekID);
                preparedStatement.setString(2, description);

                preparedStatement.executeUpdate();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                //
                preparedStatement = connection.prepareStatement("""
                        Update subjectDesc
                        Set descriptionInfo=?
                        Where teachingWeek =?
                        """);

                preparedStatement.setString(1, description);
                preparedStatement.setInt(2, weekID);

                preparedStatement.executeUpdate();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    }

    //
    public boolean addCour(int weekID, String link, String name) {
        //
        link = link.trim();
        name = name.trim();

        //
        if (checkCour(link)) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Insert into subjectCour
                    (cour_link,cour_name,teachingWeek)
                    Values (?,?,?)""");

            preparedStatement.setString(1, link);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public boolean addTD(int weekID, String link, String name) {
        //
        link = link.trim();
        name = name.trim();

        //
        if (checkTD(link)) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Insert into subjectTD
                    (td_link,td_name,teachingWeek)
                    Values (?,?,?)""");

            preparedStatement.setString(1, link);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public boolean addTP(int weekID, String link, String name) {
        //
        link = link.trim();
        name = name.trim();

        //
        if (checkTP(link)) {
            return false;
        }

        try {
            //
            preparedStatement = connection.prepareStatement("""
                    Insert into subjectTP
                    (tp_link,tp_name,teachingWeek)
                    Values (?,?,?)""");

            preparedStatement.setString(1, link);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, weekID);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //
    public String getDesc(int weekID) {
        //
        String description = "";
        try {
            preparedStatement = connection.prepareStatement("""
                    select descriptionInfo from subjectDesc
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            //
            description = resultSet.getString(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return description;
    }

    //
    public HashMap<Integer, String> getDescs(List<Integer> weekIDs) {
        //
        HashMap<Integer, String> descriptions = new HashMap<>();

        //
        for (Integer weekID : weekIDs) {
            //
            String description = this.getDesc(weekID);
            descriptions.put(weekID, description);
        }

        return descriptions;
    }


    //
    public HashMap<String, String> getCour(int weekID) {
        //
        HashMap<String, String> cours = new HashMap<>();

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

                cours.put(courLink, courName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cours;
    }

    //
    public HashMap<Integer, HashMap<String, String>> getCours(List<Integer> weekIDs) {
        //
        HashMap<Integer, HashMap<String, String>> cours = new HashMap<>();
        HashMap<String, String> cour;

        //
        for (Integer weekID : weekIDs) {
            //
            cour = this.getCour(weekID);
            cours.put(weekID, cour);

        }

        return cours;
    }

    //
    public HashMap<String, String> getTD(int weekID) {
        //
        HashMap<String, String> tds = new HashMap<>();

        try {
            preparedStatement = connection.prepareStatement("""
                    select td_link,td_name from subjectTD
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                String tdLink = resultSet.getString(1);
                String tdName = resultSet.getString(2);

                tds.put(tdLink, tdName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tds;
    }

    //
    public HashMap<Integer, HashMap<String, String>> getTDs(List<Integer> weekIDs) {
        //
        HashMap<Integer, HashMap<String, String>> tds = new HashMap<>();
        HashMap<String, String> td;

        //
        for (Integer weekID : weekIDs) {
            //
            td = this.getTD(weekID);
            tds.put(weekID, td);

        }

        return tds;
    }

    //
    public HashMap<String, String> getTP(int weekID) {
        //
        HashMap<String, String> tps = new HashMap<>();

        try {
            preparedStatement = connection.prepareStatement("""
                    select tp_link,tp_name from subjectTP
                    where teachingWeek = ?""");

            preparedStatement.setInt(1, weekID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                String tpLink = resultSet.getString(1);
                String tpName = resultSet.getString(2);

                tps.put(tpLink, tpName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tps;
    }

    //
    public HashMap<Integer, HashMap<String, String>> getTPs(List<Integer> weekIDs) {
        //
        HashMap<Integer, HashMap<String, String>> tps = new HashMap<>();
        HashMap<String, String> tp;

        //
        for (Integer weekID : weekIDs) {
            //
            tp = this.getTP(weekID);
            tps.put(weekID, tp);

        }

        return tps;
    }


    //
    public boolean updateCour(int weekID, String link, String new_link, String new_Name) {
        //

        if (weekID == 0) {
            return false;
        }

        //
        if (checkCour(link)) {
            //
            if (!new_link.isBlank()) {
                //
                try {
                    preparedStatement = connection.prepareStatement("""
                            update subjectCour
                            set cour_link=?
                            where cour_link=?""");

                    preparedStatement.setString(1, new_link);
                    preparedStatement.setString(2, link);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!new_Name.isBlank()) {
                //
                try {
                    preparedStatement = connection.prepareStatement("""
                            update subjectCour
                            set cour_name=?
                            where cour_link=?""");

                    preparedStatement.setString(1, new_Name);
                    preparedStatement.setString(2, link);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;

    }

    //
    public boolean updateTD(int weekID, String link, String new_link, String new_Name) {
        //

        if (weekID == 0) {
            return false;
        }


        //
        if (checkTD(link)) {
            //
            if (!new_link.isBlank()) {
                //
                try {
                    preparedStatement = connection.prepareStatement("""
                            update subjectTD
                            set td_link=?
                            where td_link=?""");

                    preparedStatement.setString(1, new_link);
                    preparedStatement.setString(2, link);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!new_Name.isBlank()) {
                //
                try {
                    preparedStatement = connection.prepareStatement("""
                            update subjectTD
                            set td_name=?
                            where td_link=?""");

                    preparedStatement.setString(1, new_Name);
                    preparedStatement.setString(2, link);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;

    }

    //
    public boolean updateTP(int weekID, String link, String new_link, String new_Name) {
        //

        if (weekID == 0) {
            return false;
        }


        //
        if (checkTP(link)) {
            //
            if (!new_link.isBlank()) {
                //
                try {
                    preparedStatement = connection.prepareStatement("""
                            update subjectTP
                            set tp_link=?
                            where tp_link=?""");

                    preparedStatement.setString(1, new_link);
                    preparedStatement.setString(2, link);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!new_Name.isBlank()) {
                //
                try {
                    preparedStatement = connection.prepareStatement("""
                            update subjectTP
                            set tp_name=?
                            where tp_link=?""");

                    preparedStatement.setString(1, new_Name);
                    preparedStatement.setString(2, link);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;

    }


    //
    public boolean deleteCour(int weekID, String link) {
        //
        if (weekID == 0) {
            return false;
        }

        //
        if (checkCour(link)) {
            //
            try {
                preparedStatement = connection.prepareStatement("""
                        delete from subjectCour
                        where cour_link=?""");

                preparedStatement.setString(1,link);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            return false;
        }
        //
        return true;
    }

    //
    public boolean deleteTD(int weekID, String link) {
        //
        if (weekID == 0) {
            return false;
        }

        //
        if (checkTD(link)) {
            //
            try {
                preparedStatement = connection.prepareStatement("""
                        delete from subjectTD
                        where td_link=?""");

                preparedStatement.setString(1,link);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            return false;
        }
        //
        return true;
    }

    //
    public boolean deleteTP(int weekID, String link) {
        //
        if (weekID == 0) {
            return false;
        }

        //
        if (checkTP(link)) {
            //
            try {
                preparedStatement = connection.prepareStatement("""
                        delete from subjectTP
                        where tp_link=?""");

                preparedStatement.setString(1,link);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            return false;
        }
        //
        return true;
    }


}