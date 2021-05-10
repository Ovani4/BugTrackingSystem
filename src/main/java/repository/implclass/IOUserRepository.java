package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.TaskController;
import model.Task;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.UserRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IOUserRepository implements UserRepository {
    private final Gson gsonUserRepository = new Gson();
    private List<User> listUsers;
    private final String FILE_PATH_USER = "src/main/resources/users.json";
    private static final Logger logger = LogManager.getRootLogger();
    private final TaskController useTaskController = new TaskController();

    @Override
    public List<User> getAll() {
        if (getListFromFile(FILE_PATH_USER) == null) {
            System.err.println("Данные о пользователях отсутствуют");
            logger.info("возвращен пустой лист");
            return new ArrayList<>();
        } else {
            logger.info("получен список пользователей");
            return getListFromFile(FILE_PATH_USER);
        }
    }

    @Override
    public void save(User user) {
        if (getListFromFile(FILE_PATH_USER) == null) {
            listUsers = new ArrayList<>();
            listUsers.add(user);
            System.out.println("Пользователь успешно сохранен.");
            logger.info("пользователь успешно сохранен");
        } else {
            listUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
            listUsers.add(user);
            System.out.println("Пользователь успешно сохранен.");
            logger.info("пользователь успешно сохранен");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
            bw.write(gsonUserRepository.toJson(listUsers));
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении пользователя.");
            logger.info("ошибка ввода-вывода при сохранении пользователя " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer integer) {
        listUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
        for (Task task :
                useTaskController.getAll()) {
            if (task.getUser().getId().equals(integer)) {
                System.err.println("Для данного пользователя существует задача: " + task.getTheme());
                logger.info("объект не удален, за ним значится задача " + task.getTheme());
                break;
            }
            else if (!task.getUser().getId().equals(integer)){
                System.err.println("Пользователь с данным id не обнаружен");
                logger.info("Пользователь с введенным id отсутствует");
            }else {
                listUsers.removeIf(user -> user.getId().equals(integer));
                System.out.println("Пользователь успешно удален.");
                logger.info("пользователь успешно удален");
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
                    bw.write(gsonUserRepository.toJson(listUsers));
                } catch (IOException e) {
                    System.err.println("Ошибка при удалении пользователя.");
                    logger.info("ошибка при записи в файл " + e.getMessage());
                }
            }
        }
    }
    @Override
    public Integer generateId(){
        int taskId = getAll().size() + 1;
        listUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
        for (User user : listUsers) {
            if (user.getId() == taskId)
                taskId++;
        }
        return taskId;
    }
    @Override
    public List<User> getListFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((strFromFile = br.readLine()) != null) {
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        } catch (IOException e) {
            logger.info("ошибка ввода-вывода при парсинге файла пользователей " + e.getMessage());
        }
        Type userType = new TypeToken<List<User>>() {
        }.getType();
        listUsers = gsonUserRepository.fromJson(sb.toString(), userType);
        logger.info("парсинг файла пользователей успешно совершен");
        return listUsers;
    }
}
