package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.TaskController;
import model.Project;
import model.Task;

import repository.ProjectRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class IOProjectRepository implements ProjectRepository {

    private Gson gson = new Gson();
    private List<Project> mProject;
    private final String FILE_PATH_PROJECTS = "src/main/resources/projects.json";

    public IOProjectRepository() {
    }

    @Override
    public List<Project> getAll() {

        if (getListFromFile(FILE_PATH_PROJECTS) == null) {
            System.err.println("Данные о существующих проектах отсутствуют");
            logger.info("возвращен пустой лист");
            return new ArrayList<>();
        } else {
            logger.info("получен список проектов");
            return getListFromFile(FILE_PATH_PROJECTS);
        }
    }

    @Override
    public void save(Project project) {
        if (getListFromFile(FILE_PATH_PROJECTS) == null) {
            mProject = new ArrayList<>();
            mProject.add(project);
            System.out.println("Проект успешно сохранен.");
            logger.info("проект успешно сохранен");
        } else {
            mProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
            mProject.add(project);
            System.out.println("Проект успешно сохранен.");
            logger.info("проект успешно сохранен");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_PROJECTS))) {
            bw.write(gson.toJson(mProject));
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении проекта.");
            logger.info("ошибка ввода-вывода при сохранении проекта " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer integer) {
        TaskController tc = new TaskController();
        mProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
        for (Task task : tc.getAll()) {
            if (task.getProject().getId().equals(integer)) {
                System.err.println("Для данного проекта существует задача: " + task.getTheme());
                logger.info("проект не удален, за ним значится задача " + task.getTheme());
            } else {
                mProject.removeIf(project -> project.getId().equals(integer));
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_PROJECTS))) {
                    bw.write(gson.toJson(mProject));
                    System.out.println("Проект успешно удален.");
                    logger.info("проект успешно удален");
                } catch (IOException e) {
                    System.err.println("Ошибка при удалении проекта.");
                    logger.info("ошибка при записи в файл " + e.getMessage());
                }
            }
        }
    }
    @Override
    public Integer generateId(){
        int taskId = getAll().size() + 1;
        mProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
        for (Project project : mProject) {
            if (project.getId() == taskId)
                taskId++;
        }
        return taskId;
    }

    private List<Project> getListFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((strFromFile = br.readLine()) != null) {
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        } catch (IOException e) {
            logger.info("ошибка ввода-вывода при парсинге файла проектов " + e.getMessage());
        }
        Type projectType = new TypeToken<List<Project>>() {
        }.getType();
        mProject = gson.fromJson(sb.toString(), projectType);
        logger.info("парсинг файла проектов успешно совершен");
        return mProject;
    }

}
