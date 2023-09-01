package Ui;
import Task.*;
import Duke.*;
import TaskList.TaskList;
import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello from " + "BloopBot");
        System.out.println("My name is BloopBloop");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void showError(DukeException e) {
        System.out.println("Error:" + e.getMessage());
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showCommands() {
        System.out.println(" List of Commands: Add, Deadline, Event, Todo, Echo, Mark, Unmark, Delete, Bye");
        System.out.println("1. Add - Add a task to the list");
        System.out.println("2. Deadline - Add a task with a deadline");
        System.out.println("3. Event - Add an event task");
        System.out.println("4. Todo - Add a todo task");
        System.out.println("5. Echo - Echo a message");
        System.out.println("6. Mark - Mark a task as done");
        System.out.println("7. Unmark - Unmark a task as done");
        System.out.println("8. Delete - Delete a task");
        System.out.println("9. Bye - Exit the program");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        taskList.displayTasks();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showDeletedTask(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    public void showEcho(String message) {
        System.out.println("Echo: " + message);
    }

    public void showTaskUnmarked(Task taskToUnmark) {
        System.out.println("Task unmarked: " + taskToUnmark.getDescription());
    }

    public void showTaskMarked(Task taskToUnmark) {
        System.out.println("Task marked: " + taskToUnmark.getDescription());
    }

}
