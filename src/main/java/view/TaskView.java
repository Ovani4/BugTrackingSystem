package view;

import controller.TaskController;
import model.Task;
import repository.TaskRepository;
import java.util.Scanner;

public class TaskView {
    public TaskView() {
        TaskController tc = new TaskController();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean bool = true;
        System.out.println(new StringBuilder().
                append("Для создания новой задачи введи 1;\n").
                append("Для удаления задачи по id введи 2;\n").
                append("Для получения списка всех задач введи 3;\n").
                append("Для получения списка всех задач конкретного исполнителя введи 4;\n").
                append("Для возврата в предыдущее меню введи 0;")
                .toString());
        while (bool) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Task task = new Task();
                    Integer taskId = tc.getAll().size() + 1;
                    task.setId(taskId);
                    System.out.println("Выбери проект: ");
                    //выбор проекта из листа по id
                    System.out.println("Выбери исполнителя: ");
                    //выбор исполнителя из листа по id
                    System.out.println("Введи тему задачи: ");
                    task.setTheme(scanner.nextLine());
                    System.out.println("Введи описание задачи: ");
                    task.setDescription(scanner.nextLine());
                    System.out.println("Выбери тип задачи: ");
                    //Выбор задачи из энума type
                    System.out.println("Выбери приоритет задачи: ");
                    //Выбор приоритета задачаи из энума priority

                    break;
                case 2:
                    System.out.println("Введи id задачи которую надо удалить: ");
                    tc.deleteById(scanner.nextInt());
                    break;
                case 3:
                    tc.getAll();
                    break;
                case 4:
                    System.out.println("Введи id исполнителя");
                    TaskRepository.getAllById(scanner.nextInt());
                    break;
                case 0:
                    MainView mv = new MainView();
                    bool = false;
                    break;
                default:
                    System.out.println(new StringBuilder().
                            append("Для создания новой задачи введи 1;\n").
                            append("Для удаления задачи по id введи 2;\n").
                            append("Для получения списка всех задач введи 3;\n").
                            append("Для получения списка всех задач конкретного исполнителя введи 4;\n").
                            append("Для возврата в предыдущее меню введи 0;")
                            .toString());
                    break;
            }
        }
    }
}
