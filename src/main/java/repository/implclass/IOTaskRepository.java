package repository.implclass;

import model.Task;
import repository.TaskRepository;

import java.util.List;

public class IOTaskRepository implements TaskRepository {

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public void save(Task task) {
    }

    @Override
    public void deleteById(Integer integer) {
    }



}
