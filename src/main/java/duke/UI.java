package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The duke.UI class contains methods that generate duke.UI elements
 * such as line spaces
 *
 * @author Zi Xiang
 * @version CS2103T AY23/24 Sem 1
 */
public class UI {
    /** Generates a line spacer */
    Scanner s;

    public UI() {
        this.s = new Scanner(System.in);
    }

    public void line(){
        System.out.println("\n----------------------------------------");
    }

    public void showLoadingError() {
        this.line();
        System.out.println("File was not found on hard drive");
        System.out.println("Attempting to create file...");
        this.line();
    }

    public void showWelcome() {
        this.line();
        System.out.println("Hello, I'm Prawn");
        System.out.println("What would you like me to do sire?");
        this.line();
    }

    public void showIoError() {
        this.line();
        System.out.println("Error in creating file");
        this.line();
    }

    public String readCommand() {
        return s.nextLine();
    }

    public void showUnknownCommand() {
        System.out.println("I do not understand this command");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showAddMessage(Task t, int size) {
        System.out.println("Got it, will add task...");
        System.out.println(t);
        System.out.println("Now, you have " + size + " task(s)");
    }

    public void showMarkMessage() {
        System.out.println("Alright, it has been marked");
    }

    public void showUnmarkMessage() {
        System.out.println("Alright, it has been unmarked");
    }

    public void showDeleteMessage(Task t, int size) {
        System.out.println("Sigh... fine, removing...");
        System.out.println(t);
        System.out.println("Now, you have " + size + " task(s)");
    }

    public void showOutOfBounds() {
        System.out.println("Cannot access out of bounds index");
    }

    public void showFoundTask(ArrayList<Task> tasks) {
        System.out.println("Here are the matching task in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

}
