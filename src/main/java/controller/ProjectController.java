package controller;

import model.Project;
import repository.ProjectRepository;
import repository.implclass.IOProjectRepository;

import java.util.List;

public class ProjectController {
    ProjectRepository pr = new IOProjectRepository();

    public List<Project> getAll(){
        return pr.getAll();
    }

    public void save(Project project){
        pr.save(project);
    }

    public void deleteById(Integer integer){
        pr.deleteById(integer);
    }
}
