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

    Gson gson = new Gson();
    List<Project> mProject;
    private final String FILE_PATH_PROJECTS = "src/main/resources/projects.json";

    @Override
    public List<Project> getAll() {

        if (getListFromFile(FILE_PATH_PROJECTS) == null)
            return new ArrayList<>();
        else
            return getListFromFile(FILE_PATH_PROJECTS);
    }

    @Override
    public void save(Project project) {
        if (getListFromFile(FILE_PATH_PROJECTS) == null) {
            mProject = new ArrayList<>();
            mProject.add(project);
        } else {
            mProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
            mProject.add(project);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_PROJECTS))) {
            bw.write(gson.toJson(mProject));
        } catch (IOException e) {
            //add log
        }
    }

    @Override
    public void deleteById(Integer integer) {
        TaskController tc = new TaskController();
        mProject = new ArrayList<>(getListFromFile(FILE_PATH_PROJECTS));
        for (Task task: tc.getAll()) {
            if (task.getProject().getId().equals(integer))
                System.out.println("Для данного проета существует задача: " + task.getTheme());
            else {
                mProject.removeIf(project -> project.getId().equals(integer));
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_PROJECTS))) {
                    bw.write(gson.toJson(mProject));
                } catch (IOException e) {
                    //add log
                }
            }
        }
    }

    private List<Project> getListFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        String strFromFile;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((strFromFile = br.readLine()) != null) {
                sb.append(strFromFile.replaceAll(" ", ""));
            }
        }  catch (IOException e) {
            //add log
        }
        Type projectType = new TypeToken<ArrayList<Project>>() {
        }.getType();
        mProject = gson.fromJson(sb.toString(), projectType);
        //add log
        return mProject;
    }

}
