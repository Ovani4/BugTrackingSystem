package repository.implclass;

import model.Project;
import model.Task;
import repository.TaskRepository;

import java.util.List;

public class IOTaskRepository implements TaskRepository {

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
    }



}
