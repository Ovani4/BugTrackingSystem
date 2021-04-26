package view;

import controller.UserController;
import model.User;
import java.util.Scanner;

public class UserView {
    public UserView() {
        UserController uc = new UserController();
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println(new StringBuilder().
                append("Для создания нового пользователя введи 1;\n").
                append("Для удаления пользоватея по id введи 2;\n").
                append("Для получения списка всех задач введи 3;\n").
                append("Для возврата в предыдущее меню введи 0;")
                .toString());
        while (true) {
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
                    break;
                case 2:
                    System.out.println("Введи id пользователя которого необходимо удалить: ");
                    uc.deleteById(scanner.nextInt());
                    break;
                case 3:
                    uc.getAll();
                    break;
                case 0:
                    MainView mv = new MainView();
                    break;
                default:
                    System.out.println(new StringBuilder().
                            append("Для создания нового пользователя введи 1;\n").
                            append("Для удаления пользоватея по id введи 2;\n").
                            append("Для возврата в предыдущее меню введи 0;\n")
                            .toString());
                    break;
            }
        }
    }
}
