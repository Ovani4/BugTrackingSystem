package model;

public class Task {

    private Integer id;
    private Project project;
    private User user;
    private String theme;
    private String description;
    private Type type;
    private Priority priority;

    public Task() {
    }

    public Task(Integer id, Project project, User user, String theme, String description, Type type, Priority priority) {
        this.id = id;
        this.project = project;
        this.user = user;
        this.theme = theme;
        this.description = description;
        this.type = type;
        this.priority = priority;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", project=" + project +
                ", user=" + user +
                ", theme='" + theme + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", priority=" + priority +
                '}';
    }
}
