package repository.implclass;

import model.Project;
import repository.ProjectRepository;

import java.util.List;

public class IOProjectRepository implements ProjectRepository {

    @Override
    public List<Project> getAll() {
        return null;
    }

    @Override
    public void save(Project project) {
    }

    @Override
    public void deleteById(Integer integer) {

    }

}
