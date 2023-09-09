package dre.ui;

import java.util.Scanner;
import dre.task.TaskList;
import dre.exception.DreException;
import dre.task.Task;


public class Ui {

    public void showLoadingError() {
        System.out.println("Error reading tasks from file.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Dre");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showTasks(TaskList tasks) throws DreException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println((i) + ". " + tasks.getTask(i));
        }
    }
    public void showUnmarkedTask(Task task) {
        System.out.println("Ok! I've marked this task as undone:");
        System.out.println(task);
    }

    public void showDeletedTask(Task task) {
        System.out.println("Task deleted:");
        System.out.println(task);
    }

    public void showAddedTask(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showFoundTasks(TaskList tasks) throws DreException {
        if (tasks.size() < 1) {
            System.out.println("Sorry, no tasks match that description.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println((i) + "." + tasks.getTask(i));
        }
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
