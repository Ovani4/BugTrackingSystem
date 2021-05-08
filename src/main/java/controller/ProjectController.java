package controller;

import model.Project;
import repository.ProjectRepository;
import repository.implclass.IOProjectRepository;

import java.util.List;
import java.util.Scanner;

public class ProjectController {
    private ProjectRepository pr = new IOProjectRepository();
    private Scanner scanner = new Scanner(System.in);

    public List<Project> getAll() {
        return pr.getAll();
    }

    public void save() {
        Project project = new Project();
        Integer projectId = pr.generateId();
        project.setId(projectId);
        System.out.println("Введи имя проекта: ");
        project.setTitle(scanner.nextLine());
        pr.save(project);
        System.out.println("Создан проект" + project.toString());
    }

    public void deleteById() {
        System.out.println("Введи id проекта который надо удалить: ");
        pr.deleteById(Integer.parseInt(scanner.nextLine()));
    }
}
