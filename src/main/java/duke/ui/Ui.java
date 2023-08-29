package duke.ui;

import duke.parser.Parser;
import duke.tasks.Task;

import java.util.ArrayList;
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

    /**
     * Prints out the list of tasks which match with the keyword.
     * @param tasks List of Filtered Tasks.
     */
    public void showFind(ArrayList<Task> tasks) {
        this.showLine();
        if (tasks.size() == 0) {
            System.out.println("There are no matching tasks with your entered task!");
        } else {
            System.out.println("The following tasks match with your entered task!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ") " + tasks.get(i).toString());
            }
        }
        this.showLine();
    }
}
