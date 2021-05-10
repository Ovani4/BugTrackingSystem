package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MainView {
    private static final Logger logger = LogManager.getRootLogger();
    private Scanner scan = new Scanner(System.in);
    private StringBuilder mainViewMenu = new StringBuilder();

    public void startView(){
        logger.info("приложение запущено");
        int choice;
        boolean boolmv = true;

        mainViewMenu.
                append("Для создания, удаления, получения списка пользователей введи 1;\n").
                append("Для создания, удаления, получения списка проектов введи 2;\n").
                append("Для создания, удаления, получения списка задач введи 3;\n").
                append("Для выхода из программы введи 0;");

        System.out.println(mainViewMenu);
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
                    logger.info("закрытие приложения");
                    boolmv = false;
                    break;
                default:
                    System.out.println(mainViewMenu);
            }
        }
    }
}
