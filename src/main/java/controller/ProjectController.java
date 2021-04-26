package controller;

import model.Project;
import model.Task;
import repository.ProjectRepository;
import repository.implclass.IOProjectRepository;

import java.util.List;

public class ProjectController {
    ProjectRepository pr = new IOProjectRepository();

    public List<Project> getAll(){
        return pr.getAll();
    }

    public Project save(Project project){
        return pr.save(project);
    }

    public void deleteById(Integer integer){
        pr.deleteById(integer);
    }
}
