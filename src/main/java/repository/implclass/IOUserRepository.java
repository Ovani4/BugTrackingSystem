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
    private static Logger logger;
    Gson gson = new Gson();
    List<User> mUsers;
    private final String FILE_PATH_USER = "src/main/resources/users.json";

    public IOUserRepository() {
        logger = LogManager.getRootLogger();
    }

    @Override
    public List<User> getAll() {
        if (getListFromFile(FILE_PATH_USER) == null) {
            logger.info("возвращен пустой лист");
            return new ArrayList<>();
        }
        else {
            logger.info("получен список пользователей");
            return getListFromFile(FILE_PATH_USER);
        }
    }

    @Override
    public void save(User user) {
        if (getListFromFile(FILE_PATH_USER) == null) {
            mUsers = new ArrayList<>();
            mUsers.add(user);
            logger.info("пользователь успешно сохранен");
        } else {
            mUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
            mUsers.add(user);
            logger.info("пользователь успешно сохранен");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
            bw.write(gson.toJson(mUsers));
        } catch (IOException e) {
            logger.info("ошибка ввода-вывода при сохранении пользователя");
        }
    }

    @Override
    public void deleteById(Integer integer) {
        TaskController tc = new TaskController();
        mUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
        for (Task task :
                tc.getAll()) {
            if (task.getUser().getId().equals(integer)) {
                System.out.println("Для данного пользователя существует задача: " + task.getTheme());
                logger.info("объект не удален, за ним значится задача");
            }
            else {
                mUsers.removeIf(user -> user.getId().equals(integer));
                logger.info("пользователь успешно удален");
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
                    bw.write(gson.toJson(mUsers));
                } catch (IOException e) {
                    logger.info("ошибка при записи в файл");
                }
            }
        }
    }

    private List<User> getListFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((strFromFile = br.readLine()) != null) {
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        }  catch (IOException e) {
            logger.info("ошибка ввода-вывода при парсинге файла пользователей");
        }
        Type userType = new TypeToken<ArrayList<User>>() {
        }.getType();
        mUsers = gson.fromJson(sb.toString(), userType);
        logger.info("парсинг файла пользователей успешно совершен");
        return mUsers;
    }
}
