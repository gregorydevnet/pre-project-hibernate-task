package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Grigory", "Orlov", (byte) 30);
        userService.saveUser("Alexander", "Ivanov", (byte) 25);
        userService.saveUser("Pavel", "Isaev", (byte) 35);
        userService.saveUser("Maxim", "Sidorov", (byte) 23);
        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
