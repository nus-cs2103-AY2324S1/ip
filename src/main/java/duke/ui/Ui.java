package duke.ui;

import duke.task.Task;

import java.util.List;

public class Ui {

    String name;
    public Ui (String name) {
        this.name = name;
    }

    public void showHelloMessage() {
        showDottedLine();
        System.out.println("Hello! I'm Snake CYQJ");
        System.out.println("What can I do for you?");
        showDottedLine();
    }

    public static void showDottedLine() {
        System.out.println("____________________________________________________________");
    }

    public static void showGoodbyeMessage() {
        Ui.showDottedLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.showDottedLine();
    }

    public static void showDeleteTaskMessage(List<Task> tasks, int index) {
        showDottedLine();
        System.out.println("Noted. I've removed this duke.task:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
        showDottedLine();
    }

    public static void showAddTaskMessage(List<Task> tasks) {
        showDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
        showDottedLine();
    }

    public static void showListTasksMessage(List<Task> tasks) {
        showDottedLine();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        showDottedLine();
    }

    public static void showMarkAsDoneMessage(List<Task> tasks, int index) {
        showDottedLine();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index - 1).markAsDone();
        System.out.println(tasks.get(index - 1));
        showDottedLine();
    }

    public static void showMarkAsUndoneMessage(List<Task> tasks, int index) {
        showDottedLine();
        System.out.println("OK, I've marked this task as not done yet:");
        tasks.get(index - 1).markAsUndone();
        System.out.println(tasks.get(index - 1));
        showDottedLine();
    }

    public static void showErrorSavingToFileMessage() {
        System.out.println("Error saving data to file.");
    }

    public static void showErrorLoadingFromFileMessage() {
        System.out.println("Error loading data from file.");
    }


}
