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
import java.util.Iterator;
import java.util.List;

public class IOUserRepository implements UserRepository {
    private Gson gsonUserRepository = new Gson();
    private List<User> listUsers;
    private final String FILE_PATH_USER = "src/main/resources/users.json";
    private static final Logger loggerUserRepository = LogManager.getRootLogger();

    @Override
    public List<User> getAll() {
        if (getListFromFile(FILE_PATH_USER) == null) {
            System.err.println("Данные о пользователях отсутствуют");
            loggerUserRepository.info("возвращен пустой лист");
            return new ArrayList<>();
        } else {
            loggerUserRepository.info("получен список пользователей");
            return getListFromFile(FILE_PATH_USER);
        }
    }

    @Override
    public void save(User user) {
        if (getListFromFile(FILE_PATH_USER) == null) {
            listUsers = new ArrayList<>();
            listUsers.add(user);
            System.out.println("Пользователь успешно сохранен.");
            loggerUserRepository.info("пользователь успешно сохранен");
        } else {
            listUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
            listUsers.add(user);
            System.out.println("Пользователь успешно сохранен.");
            loggerUserRepository.info("пользователь успешно сохранен");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
            bw.write(gsonUserRepository.toJson(listUsers));
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении пользователя.");
            loggerUserRepository.info("ошибка ввода-вывода при сохранении пользователя " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer integer) {
        TaskController useTaskController = new TaskController();
        listUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
        boolean canBeDeleted = true;
        Iterator<User> userIterator = listUsers.iterator();
        while (userIterator.hasNext()) {
            User nextUser = userIterator.next();
            if (nextUser.getId().equals(integer)) {
                for (Task task : useTaskController.getAll()) {
                    if (task.getProject().getId().equals(nextUser.getId())) {
                        System.out.println("Для данного проекта существует задача " + task.getTheme());
                        loggerUserRepository.info("Проект не удален, за ним числется задача " + task.getTheme());
                        canBeDeleted = false;
                    }
                }
                if (canBeDeleted) {
                    userIterator.remove();
                    System.out.println("Проект успешно удален");
                    loggerUserRepository.info("проект с id " + integer + " успешно удален");
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
                        bw.write(gsonUserRepository.toJson(listUsers));
                    } catch (IOException e) {
                        System.err.println("Ошибка при удалении пользователя.");
                        loggerUserRepository.info("ошибка при записи в файл " + e.getMessage());
                    }
                }
            }
        }
    }
    @Override
    public Integer generateId(){
        int userId = getAll().size() + 1;
        listUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
        boolean isUniqueId = true;
        while (isUniqueId) {
            int counter = 0;
            for (User user : listUsers) {
                if (user.getId() == userId) {
                    userId++;
                    counter++;
                }
            }
            if (counter == 0)
                isUniqueId = false;
        }
        return userId;
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
            loggerUserRepository.info("ошибка ввода-вывода при парсинге файла пользователей " + e.getMessage());
        }
        Type userType = new TypeToken<List<User>>() {
        }.getType();
        listUsers = gsonUserRepository.fromJson(sb.toString(), userType);
        loggerUserRepository.info("парсинг файла пользователей успешно совершен");
        return listUsers;
    }
}
