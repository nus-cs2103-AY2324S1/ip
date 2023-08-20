import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Dot {
    private static final TaskList dotTaskList = TaskList.newTaskList(100);

    public static void main(String[] args) {
        Ui.welcome();
        Scanner sc = new Scanner(System.in);

        boolean isOngoing = true;
        while (isOngoing) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    isOngoing = false;
                    break;
                case "list":
                    dotTaskList.list();
                    break;
                default:
                    dotTaskList.addItem(input);
                    break;
            }
        }
        Ui.goodbye();
    }
}
