package view;

import controller.UserController;

import java.util.Scanner;

public class UserView {
    private StringBuilder sb = new StringBuilder();

    public void startView(){
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
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    uc.save();
                    System.out.println(sb);
                    break;
                case 2:
                    uc.deleteById();
                    System.out.println(sb);
                    break;
                case 3:
                    System.out.println(uc.getAll().toString());
                    System.out.println(sb);
                    break;
                case 0:
                    MainView mv = new MainView();
                    mv.startView();
                    bool = false;
                    break;
                default:
                    System.out.println(sb);
                    break;
            }
        }
    }
}
