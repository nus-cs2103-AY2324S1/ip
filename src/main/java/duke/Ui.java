package duke;

import java.io.PrintStream;
import java.util.Scanner;

// deals with interactions with the user
public class Ui {
    private static final String CHAT_BOT_NAME = "Genos";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void greet() {
        String msg = "Hello I'm " + Ui.CHAT_BOT_NAME + ".\n"
                + "Please type your command below, I will store what you said" + "\n"
                + "Usage: \"list\" to see the list of text stored, \"bye\" to exit" + "\n"
                + "\"mark [number]\" to mark task no. [number] to be done, "
                + "\"unmark [number]\" to mark it as undone" + "\n"
                + "\"todo [description]\" to add todo, \"event [description] /from [date] /to [date]\" "
                + "to add event," + "\n"
                + "\"deadline [description] /by [date]\" to add deadline";
        out.println(msg);
    }

    public void goodBye() {
        out.println("    Goodbye, Hope to see you again soon.");
    }

    public void showLine() {
        out.println("--------------------------------------------");
    }

    public void showError(String msg) {
        out.println("There was an error: " + msg);
    }

    // takes the line of user input until the next line character is given.
    public String readCommand() {
        return in.nextLine();
    }

    public void display(String msg) {
        out.println(msg);
    }
}
