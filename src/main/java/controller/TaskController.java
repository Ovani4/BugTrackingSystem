package controller;

import model.Task;
import repository.TaskRepository;
import repository.implclass.IOTaskRepository;

import java.util.List;

public class TaskController {
    TaskRepository tr = new IOTaskRepository();

    public List<Task> getAll() {
        return tr.getAll();
    }

    public Task save(Task task){
        return tr.save(task);
    }

    public void deleteById(Integer integer){
        tr.deleteById(integer);
    }
}
