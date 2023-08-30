package duke;
import java.util.Scanner;

/**
 * Encapsulates the UI of the chat bot.
 */
public class Ui {
    private final String FAREWELL = "Bye. Hope to see you again soon!";
    private final String GREETINGS = "Hello! I'm AChatBot\n" +
    "What can I do for you?";
    private Scanner scanner;

    public Ui() {
        this.greet();
        this.scanner = new Scanner(System.in);
    }
    
    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Gets input from user.
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

    private void greet() {
        System.out.println(GREETINGS);
    }

    /** 
     * Terminates the Ui.
     */
    public void close() {
        System.out.println(FAREWELL);
        this.scanner.close();
    }

    /**
     * Reopens the Ui.
     */
    public void open() {
        this.scanner = new Scanner();
    }

    public void print(String s) {
        System.out.println(s);
    }
}
