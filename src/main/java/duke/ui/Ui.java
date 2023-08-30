package duke.ui;

import duke.tasks.DukeList;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String readCommand() {
        return this.input.nextLine();
    }

    public void showLine() {
        System.out.println("_______________________________________________________________");
    }

    /**
     * This method prints out the initial greeting
     */
    public void showWelcome() {
        //Introduction
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Cleon\n" +
                " What can I do for you?\n");
    }

    public void showList(DukeList tasks) {
        this.showLine();
        tasks.displayList();
    }

    public void showLoadingError() {
        System.out.println("Issues loading past data. Creating a new tasklist from scratch");
    }

    public void showError(String errMessage) {
        System.out.println(errMessage);
    }

    public void acknowledgeAdd(int size, Task task) {
        this.showLine();
        System.out.println("Added the following task to the list.");
        System.out.println(size + ". " + task.toString());
        System.out.println("You currently have " + size + " tasks in your list.");
        this.showLine();
    }

    public void acknowledgeDelete(int index, Task task) {
        this.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(index + ". " + task.toString());
        this.showLine();
    }

    public void acknowledgeMark(int index, Task task) {
        this.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
        this.showLine();
    }

    public void acknowledgeUnmark(int index, Task task) {
        this.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
        this.showLine();
    }

    public void exit() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }
}
