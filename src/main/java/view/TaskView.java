package view;

import controller.TaskController;

import java.util.Scanner;

public class TaskView {
    private TaskController useTaskController = new TaskController();
    private StringBuilder taskViewMenu = new StringBuilder();
    private Scanner scannerTaskView = new Scanner(System.in);

    public void startView() {

        taskViewMenu.
                append("Для создания новой задачи введи 1;\n").
                append("Для удаления задачи по id введи 2;\n").
                append("Для получения списка всех задач введи 3;\n").
                append("Для получения списка всех задач конкретного исполнителя введи 4;\n").
                append("Для получения списка всех задач конкретного проекта введи 5;\n").
                append("Для возврата в предыдущее меню введи 0;");
        int choice;
        boolean bool = true;
        System.out.println(taskViewMenu);
        while (bool) {
            choice = scannerTaskView.nextInt();
            switch (choice) {
                case 1:
                    useTaskController.save();
                    System.out.println(taskViewMenu);
                    break;
                case 2:
                    useTaskController.deleteById();
                    System.out.println(taskViewMenu);
                    break;
                case 3:
                    System.out.println(useTaskController.getAll().toString());
                    System.out.println(taskViewMenu);
                    break;
                case 4:
                    useTaskController.getAllByUserId();
                    System.out.println(taskViewMenu);
                    break;
                case 5:
                    useTaskController.getAllByProjectId();
                    System.out.println(taskViewMenu);
                    break;
                case 0:
                    MainView mv = new MainView();
                    mv.startView();
                    bool = false;
                    break;
                default:
                    System.out.println(taskViewMenu);
                    break;
            }
        }
    }
}
