package controller;

import model.User;
import repository.UserRepository;
import repository.implclass.IOUserRepository;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private UserRepository useUserRepository = new IOUserRepository();
    private Scanner scanner = new Scanner(System.in);

    public List<User> getAll() {
        return useUserRepository.getAll();
    }

    public void save() {
        User user = new User();
        Integer userId = useUserRepository.generateId();
        user.setId(userId);
        System.out.println("Введи Имя: ");
        user.setFirstName(scanner.nextLine());
        System.out.println("Введи Фамилию: ");
        user.setLastName(scanner.nextLine());
        useUserRepository.save(user);
        System.out.println("Создан пользователь: " + user);
    }

    public void deleteById() {
        System.out.println("Введи id пользователя которого необходимо удалить: ");
        System.out.println(useUserRepository.getAll());
        useUserRepository.deleteById(Integer.parseInt(scanner.nextLine()));

    }


}
