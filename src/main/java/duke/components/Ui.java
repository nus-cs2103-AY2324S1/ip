package duke.components;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void greet() { // Greets user on initialisation
        System.out.println("Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n");
    }

    public void thank() { // Exits the Bot
        System.out.println("Goodbye and have a nice day.");
    }

    public String scan() {
        return scanner.nextLine();
    }

    public void showError(String error) {
        System.err.println("Error: " + error);
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Understood, I have added the following task:");
        System.out.println(task);
        System.out.println("You now have " + totalTasks + " tasks in the list.");
    }

    public void showTaskCompleted(Task task) {
        System.out.println("Congratulations on finishing the task!");
        System.out.println("I will now mark it as complete:");
        System.out.println(task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("No worries. I will now mark the task as incomplete:");
        System.out.println(task);
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Understood, I will remove the following task from your list:");
        System.out.println(task);
        System.out.println("You now have " + totalTasks + " tasks remaining.");
    }

    public void showList(TaskList taskList) {
        System.out.println(taskList.list());
    }
}
