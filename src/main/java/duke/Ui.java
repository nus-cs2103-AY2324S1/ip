package duke;

import java.util.Scanner;

public class Ui {
    public static void greet() {
        System.out.println("Hello! I'm Eepy 😸");
    }

    public static void displayOptions() {
        System.out.println("Options:");
        System.out.println("Add todo - todo name");
        System.out.println("Add deadline - deadline name /by date (in yyyy-mm-dd format)");
        System.out.println("Add event - event name /from date /to date");
        System.out.println("Mark task as done - mark index");
        System.out.println("Mark task as not done - unmark index");
        System.out.println("Delete task - delete index");
        System.out.println("List tasks - list");
        System.out.println("Exit program - bye");
    }
    public static Scanner initialiseUi() {
        System.out.println("");
        System.out.println("What can I do for you?");
        displayOptions();
        Scanner in = new Scanner(System.in);
        return in;
    }

    public static String getInput(Scanner in) {
        System.out.println("");
        String input = in.nextLine();
        System.out.println("You entered: " + input);
        return input;
    }
}
