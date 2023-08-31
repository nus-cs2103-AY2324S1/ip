package duke.ui;

import duke.tasks.Task;
import duke.tasks.Tasks;

import java.util.Scanner;

public class Ui {
    private static String line = "    __________________________________________";
    private Scanner s = new Scanner(System.in);

    public String readCommand() {
        return s.nextLine();
    }

    public void showSuccessMark(boolean isMark, Task task) {
        System.out.println(line);
        if (isMark) {
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + task);
        System.out.println(line);
    }

    public void showAddTask(Task task, int totalTasks) {
        System.out.println(line);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println(line);
    }

    public void showNoTasks() {
        System.out.println(line);
        System.out.println("    You do not have any tasks currently!");
        System.out.println(line);
    }

    public void showTasks(Tasks tasks) {
        System.out.println(line);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(String.format("    %s.%s", i + 1, task));
        }
        System.out.println(line);
    }

    public void showSuccessDelete(Task t, int totalTasks) {
        System.out.println(line);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + t);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println(line);
    }

    public void showError(String message) {
        System.out.println(line);
        System.out.println("    " + message);
        System.out.println(line);
    }

    public void showUnknownCommand() {
        System.out.println(line);
        System.out.println("    Please enter something :-)");
        System.out.println(line);
    }

    public void showSuccessLoadingStorage(String filepath) {
        System.out.println(line);
        System.out.println("    Data has been restored from " + filepath);
        System.out.println(line);
    }

    public void showErrorFileNotFound() {
        System.out.println(line);
        System.out.println("    Data file not found, creating a new one");
        System.out.println(line);
    }

    public void showErrorLoadingFile() {
        System.out.println(line);
        System.out.println("    Error creating new file, quitting program now...");
        System.out.println(line);
    }

    public void printGreetings() {
        System.out.println(line);
        System.out.println("    Hello I'm lynn the koala <3");
        System.out.println("    What can I do for you?");
        System.out.println(line);
    }

    public void printExit() {
        System.out.println(line);
        System.out.println("    Bye. Hpoe to see you again soon!");
        System.out.println(line);
    }
}
