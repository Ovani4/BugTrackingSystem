package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;
import repository.UserRepository;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOUserRepository implements UserRepository {

    Gson gson = new Gson();
    private final String FILE_PATH_USER = "src/main/resources/users.json";

    @Override
    public List<User> getAll() {
        if (getListFromFile(FILE_PATH_USER) == null)
            return new ArrayList<>();
        else
            return getListFromFile(FILE_PATH_USER);
    }

    @Override
    public void save(User user) {
        List<User> users;
        if (getListFromFile(FILE_PATH_USER) == null) {
            users = new ArrayList<>();
            users.add(user);
        } else {
            users = new ArrayList<>(getListFromFile(FILE_PATH_USER));
            users.add(user);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
            bw.write(gson.toJson(users));
        } catch (IOException e) {
            //add log
        }
    }

    @Override
    public void deleteById(Integer integer) {
        List<User> users = new ArrayList<>(getListFromFile(FILE_PATH_USER));
        users.removeIf(user -> user.getId().equals(integer));
        System.out.println(users);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
            bw.write(gson.toJson(users));
        } catch (IOException e) {

            //add log
        }
    }

    private List<User> getListFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_USER))) {
            while ((strFromFile = br.readLine()) != null) {
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        } catch (FileNotFoundException e) {
            //add log
        } catch (IOException e) {
            //add log
        }
        Type userType = new TypeToken<ArrayList<User>>() {
        }.getType();
        List<User> users = gson.fromJson(sb.toString(), userType);
        //add log
        return users;
    }
}
