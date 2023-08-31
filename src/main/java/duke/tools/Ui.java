package duke.tools;

import duke.tasks.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Ui {

    private final String LINE = "_______________________________________";
    private final String INDENTATION = "  ";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = "                     _                 _      \n"
                + " _ __ ___  ___ _ __ (_)_ __ ___  _ __ (_)_  __\n"
                + "| '__/ _ \\/ __| '_ \\| | '__/ _ \\| '_ \\| \\ \\/ /\n"
                + "| | |  __/\\__ \\ |_) | | | | (_) | | | | |>  < \n"
                + "|_|  \\___||___/ .__/|_|_|  \\___/|_| |_|_/_/\\_\\\n"
                + "              |_|                             ";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public boolean checkForCommand() {
        return scanner.hasNext();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showOutro() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showAddMessage(Task task, int size) {
        System.out.println("Got it!. I've added this task:");
        System.out.println(INDENTATION + task);
        System.out.printf("Now you have %d tasks in the list%n", size);
        System.out.println(LINE);
    }

    public void showDeleteMessage(Task task, int size) {
        System.out.println("Noted... I've removed this task:");
        System.out.println(INDENTATION + task);
        System.out.printf("Now you have %d tasks in the list%n", size);
        System.out.println(LINE);
    }

    public void showMarkMessage(Task task) {
        System.out.println("Great job completing the task! I've marked it as done.");
        System.out.println(INDENTATION + task);
        System.out.println(LINE);
    }

    public void showUnmarkMessage(Task task) {
        System.out.println("Oops... Did you mark it incorrectly?");
        System.out.println(INDENTATION + task);
        System.out.println(LINE);
    }

    public void printTasks(TaskList tasks) {
        System.out.println("Here are your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf(INDENTATION + "%d. %s%n", i + 1, task);
        }
        System.out.println(LINE);
    }

    public void printScheduledTasks(TaskList tasks, LocalDateTime datetime) {
        System.out.println("Here are your list of tasks:");
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isWithinDateRange(datetime)) {
                System.out.printf(INDENTATION + "%d. %s%n", index++, task);
            }
        }
        System.out.println(LINE);
    }

    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Respironix has encountered an issue; exiting");
    }

}
