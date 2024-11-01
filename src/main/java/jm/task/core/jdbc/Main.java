package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        //Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль (User с именем — name добавлен в базу данных)
        userService.saveUser("Ivan", "Ivanov", (byte) 27);
        userService.saveUser("Nikolai", "Khailov", (byte) 40);
        userService.saveUser("Artem", "Kuzmenkov", (byte) 27);
        userService.saveUser("Eva", "Sokolova", (byte) 20);

        //Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
        userService.dropUsersTable();
        userService.dropUsersTable();

    }
}
