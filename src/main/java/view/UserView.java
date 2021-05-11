package view;

import controller.UserController;

import java.util.Scanner;

public class UserView {
    private StringBuilder userViewMenu = new StringBuilder();
    private UserController useUserController = new UserController();
    private Scanner scannerUserView = new Scanner(System.in);

    public void startView() {
        userViewMenu.
                append("Для создания нового пользователя введи 1;\n").
                append("Для удаления пользоватея по id введи 2;\n").
                append("Для получения списка всех пользователей введи 3;\n").
                append("Для возврата в предыдущее меню введи 0;");
        int choice;
        boolean bool = true;
        System.out.println(userViewMenu);
        while (bool) {
            choice = scannerUserView.nextInt();
            switch (choice) {
                case 1:
                    useUserController.save();
                    System.out.println(userViewMenu);
                    break;
                case 2:
                    useUserController.deleteById();
                    System.out.println(userViewMenu);
                    break;
                case 3:
                    System.out.println(useUserController.getAll().toString());
                    System.out.println(userViewMenu);
                    break;
                case 0:
                    MainView mv = new MainView();
                    mv.startView();
                    bool = false;
                    break;
                default:
                    System.out.println(userViewMenu);
                    break;
            }
        }
    }
}
