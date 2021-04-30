package repository.implclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import model.User;
import repository.TaskRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOTaskRepository implements TaskRepository {

    Gson gson = new Gson();
    List<Task> mTask;
    private final String FILE_PATH_TASK = "src/main/resources/tasks.json";

    @Override
    public List<Task> getAll() {
        if (getListFromFile(FILE_PATH_TASK) == null)
            return new ArrayList<>();
        else
            return getListFromFile(FILE_PATH_TASK);
    }

    @Override
    public void save(Task task) {
        if (getListFromFile(FILE_PATH_TASK) == null) {
            mTask = new ArrayList<>();
            mTask.add(task);
        } else {
            mTask = new ArrayList<>(getListFromFile(FILE_PATH_TASK));
            mTask.add(task);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_TASK))) {
            bw.write(gson.toJson(mTask));
        } catch (IOException e) {
            //add log
        }
    }

    @Override
    public void deleteById(Integer integer) {

        mTask = new ArrayList<>(getListFromFile(FILE_PATH_TASK));
            Iterator taskIterator = mTask.iterator();
            while (taskIterator.hasNext()){
                Task nextTask = (Task) taskIterator.next();
                if (nextTask.getId().equals(integer)) {
                    taskIterator.remove();
                    System.out.println("Задача успешно удалена.");
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_TASK))) {
                        bw.write(gson.toJson(mTask));
                    } catch (IOException e) {
                        //add log
                    }
                //add log успешное удаление
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
        }  catch (IOException e) {
            //add log
        }
        Type taskType = new TypeToken<ArrayList<Task>>() {
        }.getType();
        mTask = gson.fromJson(sb.toString(), taskType);
        //add log
        return mTask;
    }

}
