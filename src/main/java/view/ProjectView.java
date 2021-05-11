package view;

import controller.ProjectController;

import java.util.Scanner;

public class ProjectView {
    private ProjectController useProjectController = new ProjectController();
    private StringBuilder projectViewMenu = new StringBuilder();
    private Scanner scannerProjectView = new Scanner(System.in);

    public void startView(){
        projectViewMenu.
                append("Для создания нового проекта введи 1;\n").
                append("Для удаления проекта по id введи 2;\n").
                append("Для получения списка всех проектов введи 3;\n").
                append("Для возврата в предыдущее меню введи 0;");

        int choice;
        boolean bool = true;
        System.out.println(projectViewMenu);
        while (bool) {
            choice = scannerProjectView.nextInt();
            switch (choice) {
                case 1:
                    useProjectController.save();
                    System.out.println(projectViewMenu);
                    break;
                case 2:
                    useProjectController.deleteById();
                    System.out.println(projectViewMenu);
                    break;
                case 3:
                    System.out.println(useProjectController.getAll().toString());
                    System.out.println(projectViewMenu);
                    break;
                case 0:
                    MainView mv = new MainView();
                    mv.startView();
                    bool = false;
                    break;
                default:
                    System.out.println(projectViewMenu);
                    break;
            }
        }
    }
}
