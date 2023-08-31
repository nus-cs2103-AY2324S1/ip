package bongo.helper;

import bongo.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner inputScanner;

    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.inputScanner.nextLine();
    }

    private String insertLines(String message) {
        return "____________________________________________________________\n" +
                message +
                "____________________________________________________________";
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        String welcomeMessage = " Hello! I'm Bongo!\n" +
                " What can I do for you?\n";
        System.out.println(insertLines(welcomeMessage));
    }

    public void showGoodbye() {
        String goodbyeMessage = " Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
        this.inputScanner.close();
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            allTasks.append(String.format(" %d. %s\n", i + 1, tasks.get(i)));
        }
        String tasksList = " Here are the tasks in your list:\n" +
                allTasks;
        System.out.println(tasksList.trim());
    }

    public void showAddedTask(Task newTask, int totalTasks) {
        String addedTaskMessage = " Got it. I've added this task:\n" +
                String.format("  %s\n", newTask) +
                String.format(" Now you have %d tasks in the list.", totalTasks);
        System.out.println(addedTaskMessage);
    }

    public void showTaskIsDone(Task task) {
        String taskStatusMessage = " Nice! I've marked this task as done:\n" + task;
        System.out.println(taskStatusMessage.trim());
    }

    public void showTaskIsUndone(Task task) {
        String taskStatusMessage = " OK, I've marked this task as not done yet:\n" + task;
        System.out.println(taskStatusMessage.trim());
    }

    public void showDeleteTask(Task task, int tasksLeft) {
        String taskDeleteMessage = " Noted. I've removed this task:\n" +
                String.format("  %s\n", task) +
                String.format(" Now you have %d tasks in the list.", tasksLeft);
        System.out.println(taskDeleteMessage);
    }

    public void showError(String errorMessage) {
        String finalErrorMessage = " Oh no! Bongo ran into an error :(\n" + String.format(" %s", errorMessage);
        System.out.println(finalErrorMessage);
    }

    public void showLoadingError() {
        String loadingErrorMessage = "Oh no! Bongo couldn't find the files, so the following files were created:";
        System.out.println(loadingErrorMessage);
    }
}
