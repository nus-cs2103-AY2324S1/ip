package duke.ui;

import duke.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String readCommand() {
        return this.input.nextLine();
    }

    /** The command to provide the lines. **/
    public void showLine() {
        System.out.println("_______________________________________________________________");
    }

    /** The command to greet the user. **/
    public void greet() {
        this.showLine();
        System.out.println("Greetings, I am Jarvis. How may I assist you today?");
        this.showLine();
    }

    /** The exit command when user types "bye" **/
    public void leave() {
        this.showLine();
        System.out.println("I shall now take my leave. Farewell!");
        this.showLine();
    }

    /** The command to show added tasks in the tasks list.
     * @param size The index to mark
     * **/
    public void showAdd(int size, Task task) {
        this.showLine();
        System.out.println("Added the following task to the list.");
        System.out.println(size + ") " + task.toString());
        System.out.println("You currently have " + size + " tasks in your list.");
        this.showLine();
    }

    /** The command to mark tasks in the tasks list.
     * @param index The index to mark
     * **/
    public void showMark(int index, Task task) {
        this.showLine();
        System.out.println("The following task is marked as complete:");
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
        this.showLine();
    }

    /** The command to mark tasks in the tasks list.
     * @param index The index to mark
     * **/
    public void showDelete(int index, Task task) {
        this.showLine();
        System.out.println("The following task has been removed:");
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
        this.showLine();
    }

    public void showUnmark(int index, Task task) {
        this.showLine();
        System.out.println("The following task has been unmarked:");
        System.out.println(index + ") " + task.toString());
        System.out.println("Is there anything else I can assist you with?");
        this.showLine();
    }

    public void showList(int size) {
        this.showLine();
        if (size == 0) {
            System.out.println("Your task list is empty! Add a task to view it here.");
        } else {
            System.out.println("Tasks displayed. Your guidance is requested.");
        }
    }

    public void throwException(String message) {
        this.showLine();
        System.out.println(message);
        this.showLine();
    }

    public LocalDateTime formatInputDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        return LocalDateTime.parse(date, formatter);
    }

}
