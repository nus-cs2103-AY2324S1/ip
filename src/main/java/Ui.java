import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner inputScanner;

    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    protected String readCommand() {
        return this.inputScanner.nextLine();
    }

    private String insertLines(String message) {
        return "____________________________________________________________\n" +
                message +
                "____________________________________________________________";
    }

    protected void showLine() {
        System.out.println("____________________________________________________________");
    }

    protected void showWelcome() {
        String welcomeMessage = " Hello! I'm Bongo!\n" +
                " What can I do for you?\n";
        System.out.println(insertLines(welcomeMessage));
    }

    protected void showGoodbye() {
        String goodbyeMessage = " Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
        this.inputScanner.close();
    }

    protected void showAllTasks(ArrayList<Task> tasks) {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            allTasks.append(String.format(" %d. %s\n", i + 1, tasks.get(i)));
        }
        String tasksList = " Here are the tasks in your list:\n" +
                allTasks;
        System.out.println(tasksList.trim());
    }

    protected void showAddedTask(Task newTask, int totalTasks) {
        String addedTaskMessage = " Got it. I've added this task:\n" +
                String.format("  %s\n", newTask) +
                String.format(" Now you have %d tasks in the list.", totalTasks);
        System.out.println(addedTaskMessage);
    }

    protected void showTaskIsDone(Task task) {
        String taskStatusMessage = " Nice! I've marked this task as done:\n" + task;
        System.out.println(taskStatusMessage.trim());
    }

    protected void showTaskIsUndone(Task task) {
        String taskStatusMessage = " OK, I've marked this task as not done yet:\n" + task;
        System.out.println(taskStatusMessage.trim());
    }

    protected void showDeleteTask(Task task, int tasksLeft) {
        String taskDeleteMessage = " Noted. I've removed this task:\n" +
                String.format("  %s\n", task) +
                String.format(" Now you have %d tasks in the list.", tasksLeft);
        System.out.println(taskDeleteMessage);
    }

    protected void showError(String errorMessage) {
        String finalErrorMessage = " Oh no! Bongo ran into an error :(\n" + String.format(" %s", errorMessage);
        System.out.println(finalErrorMessage);
    }

    protected void showLoadingError() {
        String loadingErrorMessage = "Oh no! Bongo couldn't find the files, so the following files were created:";
        System.out.println(loadingErrorMessage);
    }
}
