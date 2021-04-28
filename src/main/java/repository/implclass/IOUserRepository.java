package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.User;
import repository.UserRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IOUserRepository implements UserRepository {

    private final String FILE_PATH_USER = "src/main/resources/users.json";
    @Override
    public List<User> getAll() {
        return getListFromFile(FILE_PATH_USER);
    }

    @Override
    public User getById(Integer integer) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
    }
    private List<User> getListFromFile(String filePath){
        List<User> users = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        Gson gson = new Gson();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_USER))) {
            while ((strFromFile = br.readLine()) != null){
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        } catch (FileNotFoundException e) {
            //add log
        } catch (IOException e) {
            //add log
        }
        Type userType = new TypeToken<ArrayList<User>>(){}.getType();
        users = gson.fromJson(sb.toString(), userType);
        return users;
    }
}
