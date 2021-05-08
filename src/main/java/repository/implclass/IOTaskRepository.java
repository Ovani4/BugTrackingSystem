package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import repository.TaskRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOTaskRepository implements TaskRepository {

    private Gson gson = new Gson();
    private List<Task> mTask;
    private final String FILE_PATH_TASK = "src/main/resources/tasks.json";

    public IOTaskRepository() {
    }

    @Override
    public List<Task> getAll() {
        if (getListFromFile(FILE_PATH_TASK) == null) {
            System.out.println("Данные о существующих задачах отсутствуют");
            logger.info("возвращен пустой лист");
            return new ArrayList<>();
        } else {
            logger.info("получен список задач");
            return getListFromFile(FILE_PATH_TASK);
        }
    }

    @Override
    public void save(Task task) {
        if (getListFromFile(FILE_PATH_TASK) == null) {
            mTask = new ArrayList<>();
            mTask.add(task);
            System.out.println("Задача успешно сохранена.");
            logger.info("задача успешно сохранена");
        } else {
            mTask = new ArrayList<>(getListFromFile(FILE_PATH_TASK));
            mTask.add(task);
            System.out.println("Задача успешно сохранена.");
            logger.info("задача успешно сохранена");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_TASK))) {
            bw.write(gson.toJson(mTask));
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении задачи");
            logger.info("ошибка ввода-вывода при сохранении задачи " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer integer) {

        mTask = new ArrayList<>(getListFromFile(FILE_PATH_TASK));
        Iterator<Task> taskIterator = mTask.iterator();
        while (taskIterator.hasNext()) {
            Task nextTask = taskIterator.next();
            if (nextTask.getId().equals(integer)) {
                taskIterator.remove();
                System.out.println("Задача успешно удалена.");
                logger.info("Задача успешно удалена");
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_TASK))) {
                    bw.write(gson.toJson(mTask));
                } catch (IOException e) {
                    System.err.println("Ошибка при удалении задачи");
                    logger.info("ошибка при записи в файл " + e.getMessage());
                }
            }
        }
    }

    @Override
    public Integer generateId(){
        int taskId = getAll().size() + 1;
        mTask = new ArrayList<>(getListFromFile(FILE_PATH_TASK));
        for (Task task : mTask) {
            if (task.getId() == taskId)
                taskId++;
        }
        return taskId;
    }

    private List<Task> getListFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((strFromFile = br.readLine()) != null) {
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        } catch (IOException e) {
            logger.info("ошибка ввода-вывода при парсинге файла задач " + e.getMessage());
        }
        Type taskType = new TypeToken<List<Task>>() {
        }.getType();
        mTask = gson.fromJson(sb.toString(), taskType);
        logger.info("парсинг файла задач успешно совершен");
        return mTask;
    }

}
