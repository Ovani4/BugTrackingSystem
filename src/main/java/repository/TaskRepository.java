package repository;
import controller.TaskController;
import model.Project;
import model.Task;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public interface TaskRepository extends Repository<Task, Integer>{
    TaskController tc = new TaskController();

    static List<Task> getAllByUser(User user){
        List<Task> tasksByUser = new ArrayList<>();
        for (Task task :
                tc.getAll()) {
            if (task.getUser().getId().equals(user.getId()))
                tasksByUser.add(task);
        }
        logger.info("Получение списка задач по id пользователя.");
        return tasksByUser;
    }
    static List<Task> getAllByProject(Project project){
        List<Task> tasksByProject = new ArrayList<>();
        for (Task task :
                tc.getAll()) {
            if (task.getProject().getId().equals(project.getId()))
                tasksByProject.add(task);
        }
        logger.info("Получение списка задач по id проекта.");
        return tasksByProject;
    }
}
