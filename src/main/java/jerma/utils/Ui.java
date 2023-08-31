package jerma.utils;

import java.util.Scanner;

import jerma.tasks.Task;

public class Ui {
    private Scanner scanner;

    public void hello() {
        this.scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Jerma.");
    }

    public void bye() {
        this.scanner.close();
        System.out.println("See ya soon!");
    }

    public String readLine() {
        return this.scanner.nextLine();
    }

    public void error(String message) {
        System.out.println("Error: " + message);
    }

    public void listTasks(TaskList tasks) {
        System.out.println(tasks);
    }

    public void newTask(Task task) {
        String type = task.getClass().getName().split("\\.")[1].toLowerCase();
        System.out.println(String.format("Added %s: %s", type, task));
    }

    public void markTask(Task task) {
        System.out.println("Marked as done: \n" + task);
    }

    public void unmarkTask(Task task) {
        System.out.println("Marked as undone: \n" + task);
    }

    public void deleteTask(Task task, int numberOfTasksRemaining) {
        System.out.println(String.format(
                "Removed the task: \n%s \nYou have %d tasks remaining.", task,
                numberOfTasksRemaining));
    }
}
