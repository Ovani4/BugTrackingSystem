package controller;

import model.User;
import repository.UserRepository;
import repository.implclass.IOUserRepository;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private UserRepository ur = new IOUserRepository();
    private Scanner scanner = new Scanner(System.in);

    public List<User> getAll() {
        return ur.getAll();
    }

    public void save() {
        User user = new User();
        Integer userId = ur.getAll().size() + 1;
        user.setId(userId);
        System.out.println("Введи Имя: ");
        user.setFirstName(scanner.nextLine());
        System.out.println("Введи Фамилию: ");
        user.setLastName(scanner.nextLine());
        ur.save(user);
        System.out.println("Создан пользователь: " + user.toString());
    }

    public void deleteById() {
        System.out.println("Введи id пользователя которого необходимо удалить: ");
        System.out.println(ur.getAll());
        ur.deleteById(Integer.parseInt(scanner.nextLine()));

    }


}
