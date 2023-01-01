package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class UserManager {
    //
    private Connection connection;
    private PreparedStatement preparedStatement;
    private final String url = "jdbc:mysql://localhost:3306/TQL_APP_BD";
    private final String root = "root";
    private final String password = "password";

    //
    public UserManager() {
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
    public boolean checkUser(String email) {
        //
        email = email.trim();
        boolean foundOrNot = false;

        try {
            preparedStatement = connection.prepareStatement("select * from users where user_email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            foundOrNot = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        return foundOrNot;
    }

    //
    public boolean checkCredentials(String email, String password) {
        //
        email = email.trim();
        password = password.trim();

        try {
            preparedStatement = connection.prepareStatement("select * from users where user_email =? && user_password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public User getUser(String email) {
        //
        email = email.trim();
        User user = null;

        try {
            preparedStatement = connection.prepareStatement("select * from users where user_email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //
    public String getRank(String email) {
        //
        email = email.trim();
        String rank = null;

        try {
            preparedStatement = connection.prepareStatement("select user_rank from users where user_email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            rank = resultSet.getString("user_rank");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rank;
    }

    //
    public List<User> getUsers() {
        //
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                User user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //
    public List<User> sortByEmail() {
        //
        return this.getUsers();
    }

    //
    public List<User> sortByName() {
        //
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from users order by user_name");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                User user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //
    public List<User> sortByRank() {
        //
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from users order by user_rank");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //
                User user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //
    public int getNumberOfUsers() {
        //
        try {
            preparedStatement = connection.prepareStatement("select COUNT(*) from users");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        return 0;
    }

    //
    public boolean addUser(User user) {
        //
        if (checkUser(user.getEmail())) {
            return false;
        }

        //
        try {

            preparedStatement = connection.prepareStatement("insert into users values (?,?,?,?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRank());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public boolean deleteUser(String email) {
        //
        email = email.trim();

        if (!checkUser(email)) {
            return false;
        }

        //
        try {

            preparedStatement = connection.prepareStatement("delete from users where user_email=?");
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public boolean updateUser(String email, User user) {
        //
        email = email.trim();

        if (!checkUser(email)) {
            return false;
        }
        //
        User originalUser = this.getUser(email);

        //
        if (user.getEmail().isBlank()) {
            user.setEmail(originalUser.getEmail());
        }
        if (user.getUsername().isBlank()) {
            user.setUsername(originalUser.getUsername());
        }
        if (user.getPassword().isBlank()) {
            user.setPassword(originalUser.getPassword());
        }
        if (user.getRank().isBlank()) {
            user.setRank(originalUser.getRank());
        }

        //
        try {

            preparedStatement = connection.prepareStatement(
                    "update users set user_email=?, user_name=?, user_password=?, user_rank=?  where user_email=? ");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRank());
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
