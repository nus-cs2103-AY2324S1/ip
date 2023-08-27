package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void showLoadingError() {
        System.out.println("Unable to initialise duke.Duke.");
    }

    public static void showWelcome() {
        System.out.println("Hello I'm iP");
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("List of tasks:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("You have " + tasks.size() + " tasks in the list.");
    }

    public static String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }
}
