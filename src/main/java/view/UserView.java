package view;

import controller.UserController;
import model.User;

import java.util.Scanner;

public class UserView {
    public UserView() {
        StringBuilder sb = new StringBuilder();
                sb.
                append("Для создания нового пользователя введи 1;\n").
                append("Для удаления пользоватея по id введи 2;\n").
                append("Для получения списка всех пользователей введи 3;\n").
                append("Для возврата в предыдущее меню введи 0;").
                toString();
        UserController uc = new UserController();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean bool = true;
        System.out.println(sb);
        while (bool) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    User user = new User();
                    Integer userId = uc.getAll().size() + 1;
                    user.setId(userId);
                    System.out.println("Введи Имя: ");
                    user.setFirstName(scanner.nextLine());
                    System.out.println("Введи Фамилию: ");
                    user.setLastName(scanner.nextLine());
                    System.out.println("Создан пользователь: " + user.toString());
                    System.out.println(sb);
                    break;
                case 2:
                    System.out.println("Введи id пользователя которого необходимо удалить: ");
                    uc.deleteById(scanner.nextInt());
                    break;
                case 3:
                    System.out.println(uc.getAll().toString());
                    System.out.println(sb);
                    break;
                case 0:
                    MainView mv = new MainView();
                    bool = false;
                    break;
                default:
                    System.out.println(sb);
                    break;
            }
        }
    }
}
