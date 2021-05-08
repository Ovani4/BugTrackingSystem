package view;

import controller.TaskController;

import java.util.Scanner;

public class TaskView {
    private TaskController tc = new TaskController();
    private StringBuilder sb = new StringBuilder();

    public TaskView() {
        sb.
                append("Для создания новой задачи введи 1;\n").
                append("Для удаления задачи по id введи 2;\n").
                append("Для получения списка всех задач введи 3;\n").
                append("Для получения списка всех задач конкретного исполнителя введи 4;\n").
                append("Для получения списка всех задач конкретного проекта введи 5;\n").
                append("Для возврата в предыдущее меню введи 0;")
                .toString();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean bool = true;
        System.out.println(sb);
        while (bool) {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    tc.save();
                    System.out.println(sb);
                    break;
                case 2:
                    tc.deleteById();
                    System.out.println(sb);
                    break;
                case 3:
                    System.out.println(tc.getAll().toString());
                    System.out.println(sb);
                    break;
                case 4:
                    tc.getAllByUserId();
                    System.out.println(sb);
                    break;
                case 5:
                    tc.getAllByProjectId();
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
