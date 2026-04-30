package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, last_name VARCHAR(100) NOT NULL, age INT)";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            System.err.println("При создании таблицы произошла ошибка: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.execute(dropTableSQL);
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            System.err.println("При удалении таблицы произошла ошибка: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSQL = "INSERT users(name, last_name, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.execute(saveSQL);
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.err.println("При добавлении пользователя произошла ошибка: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String removeByIdSQL = "DELETE FROM users WHERE id='" + id + "'";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.execute(removeByIdSQL);
            System.out.println("User c id " + id + " успешно удален");
        } catch (SQLException e) {
            System.err.println("При удалении пользователя по id произошла ошибка: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAllSQL = "SELECT * FROM users";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllSQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.err.println("При получении всех пользователей произошла ошибка: ");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE users";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.executeUpdate(cleanTable);
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            System.err.println("При попытке удалить всех пользователей произошла ошибка: " + e.getMessage());
        }
    }
}
