package duke.ui;

import java.util.Scanner;

import duke.data.TaskList;
import duke.data.task.Task;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Dommi");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println("Goodbye! Hope to see you again soon! :D");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_".repeat(60));
    }

    public void showLoadingError() {
        System.out.println(".txt file not found! Creating .txt file ...");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showTaskAdded(Task task, int noTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int noTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    public void showTaskList(TaskList taskList) {
        if (!taskList.hasTasks()) {
            System.out.println("No tasks have been created.");
        } else {
            for (int i = 0; i < taskList.countTasks(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i));
            }
        }
    }

    public void showInvalidFormat() {
        System.out.println("☹ OOPS!!! Date has to be in yyyy-mm-dd format!");
    }

    public void showWriteFileError() {
        System.out.println("☹ OOPS!!! Err writing to file!");
    }

    public void showInvalidCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
