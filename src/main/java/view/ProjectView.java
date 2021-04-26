package view;

import controller.ProjectController;
import model.Project;

import java.util.Scanner;

public class ProjectView {
    public ProjectView() {
        ProjectController pc = new ProjectController();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean bool = true;
        System.out.println(new StringBuilder().
                append("Для создания нового проекта введи 1;\n").
                append("Для удаления проекта по id введи 2;\n").
                append("Для получения списка всех проектов введи 3;\n").
                append("Для возврата в предыдущее меню введи 0;")
                .toString());
        while (bool) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Project project = new Project();
                    Integer projectId = pc.getAll().size() + 1;
                    project.setId(projectId);
                    System.out.println("Введи имя проекта: ");
                    project.setTitle(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Введи id проекта который надо удалить: ");
                    pc.deleteById(scanner.nextInt());
                    break;
                case 3:
                    pc.getAll();
                    break;
                case 0:
                    MainView mv = new MainView();
                    bool = false;
                    break;
                default:
                    System.out.println(new StringBuilder().
                            append("Для создания нового проекта введи 1;\n").
                            append("Для удаления проекта по id введи 2;\n").
                            append("Для получения списка всех проектов введи 3;\n").
                            append("Для возврата в предыдущее меню введи 0;")
                            .toString());
                    break;
            }
        }
    }
}
