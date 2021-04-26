package view;

import java.util.Scanner;

public class MainView {
    public MainView() {
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean bool = true;
        System.out.println("Программа запущена.");
        System.out.println(new StringBuilder().
                append("Для создания, удаления, получения списка пользователей введи 1;\n").
                append("Для создания, удаления, получения списка проектов введи 2;\n").
                append("Для создания, удаления, получения списка задач введи 3;\n").
                append("Для выхода из программы введи 0;").toString());
        while (bool) {
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    UserView uv = new UserView();
                    break;
                case 2:
                    ProjectView pv = new ProjectView();
                    break;
                case 3:
                    TaskView tv = new TaskView();
                    break;
                case 0:
                    System.out.println("Закрытие программы...");
                    bool = false;
                    break;
                default:
                    System.out.println(new StringBuilder().
                            append("Для создания, удаления, получения списка пользователей введи 1;\n").
                            append("Для создания, удаления, получения списка проектов введи 2;\n").
                            append("Для создания, удаления, получения списка задач введи 3;\n").
                            append("Для выхода из программы введи 0;").toString());
            }
        }
    }
}
