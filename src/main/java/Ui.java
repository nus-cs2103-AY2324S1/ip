import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner inputScanner;

    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    private String readCommand() {
        return this.inputScanner.nextLine();
    }

    private String insertLines(String message) {
        return "____________________________________________________________\n" +
                message +
                "____________________________________________________________";
    }

    private void showWelcome() {
        String welcomeMessage = " Hello! I'm Bongo!\n" +
                " What can I do for you?\n";
        System.out.println(insertLines(welcomeMessage));
    }

    private void showGoodbye() {
        String goodbyeMessage = " Bye. Hope to see you again soon!\n";
        System.out.println(insertLines(goodbyeMessage));
    }

    private void showAllTasks(ArrayList<Task> tasks) {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            allTasks.append(String.format(" %d. %s\n", i + 1, tasks.get(i)));
        }
        String tasksList = " Here are the tasks in your list:\n" +
                allTasks;
        System.out.println(insertLines(tasksList));
    }

    private void showAddedTask(Task newTask, int totalTasks) {
        String addedTaskMessage = " Got it. I've added this task:\n" +
                String.format("  %s\n", newTask) +
                String.format(" Now you have %d tasks in the list.\n", totalTasks);
        System.out.println(insertLines(addedTaskMessage));
    }

    private void showTaskIsDone(Task task) {
        String taskStatusMessage = " Nice! I've marked this task as done:\n";
        System.out.println(insertLines(taskStatusMessage));
    }

    private void showTaskIsUndone(Task task) {
        String taskStatusMessage = " OK, I've marked this task as not done yet:\n";
        System.out.println(insertLines(taskStatusMessage));
    }

    private void showDeleteTask(Task task, int tasksLeft) {
        String taskDeleteMessage = " Noted. I've removed this task:\n" +
                String.format("  %s\n", task) +
                String.format(" Now you have %d tasks in the list.\n", tasksLeft);
        System.out.println(insertLines(taskDeleteMessage));
    }
}
