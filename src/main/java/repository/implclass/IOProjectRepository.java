package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.TaskController;
import model.Project;
import model.Task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.ProjectRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class IOProjectRepository implements ProjectRepository {

    private final Gson gsonProjectRepository = new Gson();
    private List<Project> listProject;
    private final String FILE_PATH_PROJECTS = "src/main/resources/projects.json";
    private static final Logger loggerProjectRepository = LogManager.getRootLogger();
    private final TaskController useTaskController = new TaskController();

    @Override
    public List<Project> getAll() {

        if (getListFromFile(FILE_PATH_PROJECTS) == null) {
            System.err.println("Данные о существующих проектах отсутствуют");
            loggerProjectRepository.info("возвращен пустой лист");
            return new ArrayList<>();
        } else {
            loggerProjectRepository.info("получен список проектов");
            return getListFromFile(FILE_PATH_PROJECTS);
        }
    }

    @Override
    public void save(Project project) {
        if (getListFromFile(FILE_PATH_PROJECTS) == null) {
            listProject = new ArrayList<>();
            listProject.add(project);
            System.out.println("Проект успешно сохранен.");
            loggerProjectRepository.info("проект успешно сохранен");
        } else {
            listProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
            listProject.add(project);
            System.out.println("Проект успешно сохранен.");
            loggerProjectRepository.info("проект успешно сохранен");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_PROJECTS))) {
            bw.write(gsonProjectRepository.toJson(listProject));
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении проекта.");
            loggerProjectRepository.info("ошибка ввода-вывода при сохранении проекта " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer integer) {
        listProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
        for (Task task : useTaskController.getAll()) {
            if (task.getProject().getId().equals(integer)) {
                System.err.println("Для данного проекта существует задача: " + task.getTheme());
                loggerProjectRepository.info("проект не удален, за ним значится задача " + task.getTheme());
                break;
            }
            else if (!task.getProject().getId().equals(integer)){
                System.err.println("Проекта с данным id не обнаружено");
                loggerProjectRepository.info("Проект с введенным id отсутствует");
            } else {
                listProject.removeIf(project -> project.getId().equals(integer));
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_PROJECTS))) {
                    bw.write(gsonProjectRepository.toJson(listProject));
                    System.out.println("Проект успешно удален.");
                    loggerProjectRepository.info("проект успешно удален");
                } catch (IOException e) {
                    System.err.println("Ошибка при удалении проекта.");
                    loggerProjectRepository.info("ошибка при записи в файл " + e.getMessage());
                }
            }
        }
    }
    @Override
    public Integer generateId(){
        int taskId = getAll().size() + 1;
        listProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
        for (Project project : listProject) {
            if (project.getId() == taskId)
                taskId++;
        }
        return taskId;
    }
    @Override
    public List<Project> getListFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((strFromFile = br.readLine()) != null) {
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        } catch (IOException e) {
            loggerProjectRepository.info("ошибка ввода-вывода при парсинге файла проектов " + e.getMessage());
        }
        Type projectType = new TypeToken<List<Project>>() {
        }.getType();
        listProject = gsonProjectRepository.fromJson(sb.toString(), projectType);
        loggerProjectRepository.info("парсинг файла проектов успешно совершен");
        return listProject;
    }

}
