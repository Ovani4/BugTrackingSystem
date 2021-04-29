package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;
import repository.UserRepository;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IOUserRepository implements UserRepository {

    Gson gson = new Gson();
    List<User> mUsers;
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

        if (getListFromFile(FILE_PATH_USER) == null) {
            mUsers = new ArrayList<>();
            mUsers.add(user);
        } else {
            mUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
            mUsers.add(user);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
            bw.write(gson.toJson(mUsers));
        } catch (IOException e) {
            //add log
        }
    }

    @Override
    public void deleteById(Integer integer) {
        mUsers = new ArrayList<>(getListFromFile(FILE_PATH_USER));
        mUsers.removeIf(user -> user.getId().equals(integer));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_USER))) {
            bw.write(gson.toJson(mUsers));
        } catch (IOException e) {

            //add log
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
            //add log
        }
        Type userType = new TypeToken<ArrayList<User>>() {
        }.getType();
        mUsers = gson.fromJson(sb.toString(), userType);
        //add log
        return mUsers;
    }
}
