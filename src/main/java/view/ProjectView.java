package view;

import controller.ProjectController;

import java.util.Scanner;

public class ProjectView {
    private ProjectController pc = new ProjectController();
    private StringBuilder sb = new StringBuilder();
    private Scanner scanner = new Scanner(System.in);

    public void startView(){
        sb.
                append("Для создания нового проекта введи 1;\n").
                append("Для удаления проекта по id введи 2;\n").
                append("Для получения списка всех проектов введи 3;\n").
                append("Для возврата в предыдущее меню введи 0;")
                .toString();

        int choice;
        boolean bool = true;
        System.out.println(sb);
        while (bool) {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    pc.save();
                    System.out.println(sb);
                    break;
                case 2:
                    pc.deleteById();
                    System.out.println(sb);
                    break;
                case 3:
                    System.out.println(pc.getAll().toString());
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
