package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public static String horizontalLine = "_".repeat(60) + "\n";
    public static void greet() {
        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
    }
    public static void sayBye() {
        System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
    }

    public static void showError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }
    
    public static void displaySearchResults(TaskList taskList, String keyword) {
        List<Task> allTasks = taskList.getTasks();
        if (allTasks.isEmpty()) {
            System.out.println("You have no tasks in your list.\n");
            return;
        }

        TaskList filteredList = new TaskList();
        for (Task task : allTasks) {
            if (task.contains(keyword)) {
                filteredList.addTask(task);
            }
        }

        if (filteredList.getTasks().isEmpty()) {
            System.out.println("You have no matching tasks in your list.\n");
            return;
        }

        System.out.println(horizontalLine + "Here are the matching items in your list: \n");
        int count = 0;
        for (Task t : filteredList.getTasks()) {
            System.out.println(++count + ". " + t.toString());
        }
        System.out.println("\n" + horizontalLine);
    }

}
