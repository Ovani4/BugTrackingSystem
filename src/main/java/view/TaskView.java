package view;

import controller.ProjectController;
import controller.TaskController;
import controller.UserController;
import model.*;
import repository.TaskRepository;
import java.util.Scanner;

public class TaskView {
    public TaskView() {
        UserController uc;
        ProjectController pc;
        StringBuilder sb = new StringBuilder();
        sb.
                append("Для создания новой задачи введи 1;\n").
                append("Для удаления задачи по id введи 2;\n").
                append("Для получения списка всех задач введи 3;\n").
                append("Для получения списка всех задач конкретного исполнителя введи 4;\n").
                append("Для получения списка всех задач конкретного проекта введи 5;\n").
                append("Для возврата в предыдущее меню введи 0;")
                .toString();
        TaskController tc = new TaskController();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean bool = true;
        System.out.println(sb);
        while (bool) {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Task task = new Task();
                    Integer taskId = tc.getAll().size() + 1;
                    task.setId(taskId);
                    System.out.println("Выбери проект по id: ");
                    pc = new ProjectController();
                    System.out.println(pc.getAll());
                    task.setProject(pc.getAll().
                            stream().
                            filter(project -> project.getId().
                                    equals(Integer.parseInt(scanner.nextLine()))).
                            findFirst().
                            orElse(null));
                    System.out.println("Выбери исполнителя по id: ");
                    uc = new UserController();
                    System.out.println(uc.getAll());
                    task.setUser(uc.getAll().
                            stream().
                            filter(user -> user.getId().
                                    equals(Integer.parseInt(scanner.nextLine()))).
                            findFirst().
                            orElse(null));
                    System.out.println("Введи тему задачи: ");
                    task.setTheme(scanner.nextLine());
                    System.out.println("Введи описание задачи: ");
                    task.setDescription(scanner.nextLine());
                    System.out.println("Выбери тип задачи: \n1. Task \n2. Bug");
                    if (Integer.parseInt(scanner.nextLine()) == 1)
                        task.setType(Type.TASK);
                    else task.setType(Type.BUG);
                    //Выбор задачи из энума type
                    System.out.println("Выбери приоритет задачи: " +
                            "\n 1. Low " +
                            "\n 2. Medium " +
                            "\n 3. Hi" );
                    if (Integer.parseInt(scanner.nextLine()) == 1)
                        task.setPriority(Priority.LOW);
                    else if (Integer.parseInt(scanner.nextLine()) == 2)
                        task.setPriority(Priority.MEDIUM);
                    else task.setPriority(Priority.HI);
                    tc.save(task);
                    System.out.println("Создана новая задача: " + task);
                    System.out.println(sb);
                    break;
                case 2:
                    System.out.println("Введи id задачи которую надо удалить: ");
                    System.out.println(tc.getAll());
                    tc.deleteById(Integer.parseInt(scanner.nextLine()));
                    System.out.println(sb);
                    break;
                case 3:
                    System.out.println(tc.getAll());
                    System.out.println(sb);
                    break;
                case 4:
                    System.out.println("Введи id исполнителя");
                    uc = new UserController();
                    System.out.println(uc.getAll());
                    User user = uc.getAll().
                            stream().
                            filter(user1 -> user1.getId().
                                    equals(Integer.parseInt(scanner.nextLine()))).
                            findFirst().
                            orElse(null);
                    System.out.println(TaskRepository.getAllByUser(user));
                    System.out.println(sb);
                    break;
                case 5:
                    System.out.println("Введи id проекта");
                    pc = new ProjectController();
                    System.out.println(pc.getAll());
                    Project project = pc.getAll().
                            stream().
                            filter(project1 -> project1.getId().
                                    equals(Integer.parseInt(scanner.nextLine()))).
                            findFirst().
                            orElse(null);
                    System.out.println(TaskRepository.getAllByProject(project));
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
