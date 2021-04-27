package repository;
import model.Task;

import java.util.List;

public interface TaskRepository extends Repository<Task, Integer>{
    static List<Task> getAllById(Integer integer){
        return null;
    }
}
