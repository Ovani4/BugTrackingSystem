package controller;

import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.TaskRepository;
import repository.implclass.IOTaskRepository;

import java.util.List;
import java.util.Scanner;


public class TaskController {
    TaskRepository useTaskRepository = new IOTaskRepository();
    Scanner scannerTaskController = new Scanner(System.in);
    UserController useUserController = new UserController();
    ProjectController useProjectController = new ProjectController();
    private static Logger logger = LogManager.getRootLogger();

    public List<Task> getAll() {
        return useTaskRepository.getAll();
    }

    public void save() {
        Task newTask = new Task();
        Integer taskId = useTaskRepository.generateId();
        newTask.setId(taskId);
        System.out.println("Выбери проект по id: ");
        System.out.println(useProjectController.getAll());
        Integer choiceProjectId = Integer.parseInt(scannerTaskController.nextLine());
        newTask.setProject(useProjectController.getAll().
                stream().
                filter(project -> project.getId().
                        equals(choiceProjectId)).
                findFirst().
                orElse(null));
        System.out.println("Выбери исполнителя по id: ");
        System.out.println(useUserController.getAll());
        Integer choiceUserId = Integer.parseInt(scannerTaskController.nextLine());
        newTask.setUser(useUserController.getAll().
                stream().
                filter(user -> user.getId().
                        equals(choiceUserId)).
                findFirst().
                orElse(null));
        System.out.println("Введи тему задачи: ");
        newTask.setTheme(scannerTaskController.nextLine());
        System.out.println("Введи описание задачи: ");
        newTask.setDescription(scannerTaskController.nextLine());
        System.out.println("Выбери тип задачи: \n1. Task \n2. Bug");
        int choiceTypeTask = Integer.parseInt(scannerTaskController.nextLine());
        if (choiceTypeTask == 1)
            newTask.setType(Type.TASK);
        else newTask.setType(Type.BUG);
        System.out.println("Выбери приоритет задачи: " +
                "\n 1. Low " +
                "\n 2. Medium " +
                "\n 3. Hi");
        int choicePriorityTask = Integer.parseInt(scannerTaskController.nextLine());
        if (choicePriorityTask == 1)
            newTask.setPriority(Priority.LOW);
        else if (choicePriorityTask == 2)
            newTask.setPriority(Priority.MEDIUM);
        else newTask.setPriority(Priority.HI);
        if (newTask.getProject() == null || newTask.getUser() == null) {
            System.out.println("Задача не может ссылаться на несуществующий проект или пользователя");
        } else {
            useTaskRepository.save(newTask);
            System.out.println("Создана новая задача: " + newTask);
        }
    }

    public void deleteById() {
        System.out.println("Введи id задачи которую надо удалить: ");
        System.out.println(useTaskRepository.getAll());
        useTaskRepository.deleteById(Integer.parseInt(scannerTaskController.nextLine()));
    }

    public void getAllByUserId() {
        System.out.println("Введи id исполнителя");
        System.out.println(useUserController.getAll());
        int userId = scannerTaskController.nextInt();
        User user = useUserController.getAll().
                stream().
                filter(user1 -> user1.getId().
                        equals(userId)).
                findFirst().
                orElse(null);
        System.out.println(TaskRepository.getAllByUser(user));
        logger.info("получение списка задач для пользователя" + user);
    }

    public void getAllByProjectId() {
        System.out.println("Введи id проекта");
        System.out.println(useProjectController.getAll());
        int projectId = scannerTaskController.nextInt();
        Project project = useProjectController.getAll().
                stream().
                filter(project1 -> project1.getId().
                        equals(projectId)).
                findFirst().
                orElse(null);
        System.out.println(TaskRepository.getAllByProject(project));
        logger.info("получение списка задач для проекта" + project);
    }
}
