package controller;

import model.*;
import repository.TaskRepository;
import repository.implclass.IOTaskRepository;

import java.util.List;
import java.util.Scanner;

import static repository.TaskRepository.logger;

public class TaskController {
    private TaskRepository tr = new IOTaskRepository();
    private Scanner scanner = new Scanner(System.in);
    private UserController uc = new UserController();
    private ProjectController pc = new ProjectController();

    public List<Task> getAll() {
        return tr.getAll();
    }

    public void save() {
        Task task = new Task();
        Integer taskId = tr.getAll().size() + 1;
        task.setId(taskId);
        System.out.println("Выбери проект по id: ");
        System.out.println(pc.getAll());
        task.setProject(pc.getAll().
                stream().
                filter(project -> project.getId().
                        equals(Integer.parseInt(scanner.nextLine()))).
                findFirst().
                orElse(null));
        System.out.println("Выбери исполнителя по id: ");
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
        System.out.println("Выбери приоритет задачи: " +
                "\n 1. Low " +
                "\n 2. Medium " +
                "\n 3. Hi");
        if (Integer.parseInt(scanner.nextLine()) == 1)
            task.setPriority(Priority.LOW);
        else if (Integer.parseInt(scanner.nextLine()) == 2)
            task.setPriority(Priority.MEDIUM);
        else task.setPriority(Priority.HI);
        if (task.getProject().equals(null) || task.getUser().equals(null)) {
            System.out.println("Задача не может ссылаться на несуществующий проект или пользователя");
        } else {
            tr.save(task);
            System.out.println("Создана новая задача: " + task);
        }
    }

    public void deleteById() {
        System.out.println("Введи id задачи которую надо удалить: ");
        System.out.println(tr.getAll());
        tr.deleteById(Integer.parseInt(scanner.nextLine()));
    }

    public void getAllByUserId() {
        System.out.println("Введи id исполнителя");
        System.out.println(uc.getAll());
        User user = uc.getAll().
                stream().
                filter(user1 -> user1.getId().
                        equals(Integer.parseInt(scanner.nextLine()))).
                findFirst().
                orElse(null);
        System.out.println(TaskRepository.getAllByUser(user));
        logger.info("получение списка задач для пользователя" + user);
    }

    public void getAllByProjectId() {
        System.out.println("Введи id проекта");
        System.out.println(pc.getAll());
        Project project = pc.getAll().
                stream().
                filter(project1 -> project1.getId().
                        equals(Integer.parseInt(scanner.nextLine()))).
                findFirst().
                orElse(null);
        System.out.println(TaskRepository.getAllByProject(project));
        logger.info("получение списка задач для проекта" + project);
    }
}
