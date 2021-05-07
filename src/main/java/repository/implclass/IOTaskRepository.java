package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.TaskRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOTaskRepository implements TaskRepository {

    private Gson gson = new Gson();
    private List<Task> mTask;
    private static Logger logger;
    private final String FILE_PATH_TASK = "src/main/resources/tasks.json";

    public IOTaskRepository() {
        logger = LogManager.getRootLogger();
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
        Iterator taskIterator = mTask.iterator();
        while (taskIterator.hasNext()) {
            Task nextTask = (Task) taskIterator.next();
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
        Type taskType = new TypeToken<ArrayList<Task>>() {
        }.getType();
        mTask = gson.fromJson(sb.toString(), taskType);
        logger.info("парсинг файла задач успешно совершен");
        return mTask;
    }

}
