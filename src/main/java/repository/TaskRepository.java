package repository;

import model.Project;
import model.Task;

import java.util.List;

public interface TaskRepository extends Repository<Task, Integer>{
    List<Project> getAllById(Integer integer);
}
