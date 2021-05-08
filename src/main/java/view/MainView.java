package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MainView {
    private static Logger logger;
    private Scanner scan = new Scanner(System.in);
    private StringBuilder sb = new StringBuilder();

    public void startView(){
        logger = LogManager.getRootLogger();
        logger.info("приложение запущено");
        int choice;
        boolean boolmv = true;

        sb.
                append("Для создания, удаления, получения списка пользователей введи 1;\n").
                append("Для создания, удаления, получения списка проектов введи 2;\n").
                append("Для создания, удаления, получения списка задач введи 3;\n").
                append("Для выхода из программы введи 0;").toString();

        System.out.println(sb);
        while (boolmv) {
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    UserView userView = new UserView();
                    userView.startView();
                    boolmv = false;
                    break;
                case 2:
                    ProjectView projectView = new ProjectView();
                    projectView.startView();
                    boolmv = false;
                    break;
                case 3:
                    TaskView taskView = new TaskView();
                    taskView.startView();
                    boolmv = false;
                    break;
                case 0:
                    System.out.println("Закрытие программы...");
                    boolmv = false;
                    break;
                default:
                    System.out.println(sb);
            }
        }
        logger.info("закрытие приложения");
    }
}
