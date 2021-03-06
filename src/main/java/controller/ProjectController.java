package controller;

import model.Project;
import repository.ProjectRepository;
import repository.implclass.IOProjectRepository;

import java.util.List;
import java.util.Scanner;

public class ProjectController {
    ProjectRepository useProjectRepository = new IOProjectRepository();
    Scanner scannerProjectController = new Scanner(System.in);

    public List<Project> getAll() {
        return useProjectRepository.getAll();
    }

    public void save() {
        Project project = new Project();
        Integer projectId = useProjectRepository.generateId();
        project.setId(projectId);
        System.out.println("Введи имя проекта: ");
        project.setTitle(scannerProjectController.nextLine());
        useProjectRepository.save(project);
        System.out.println("Создан проект" + project);
    }

    public void deleteById() {
        System.out.println("Введи id проекта который надо удалить: ");
        System.out.println(getAll());
        useProjectRepository.deleteById(Integer.parseInt(scannerProjectController.nextLine()));
    }
}
