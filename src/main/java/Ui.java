import java.util.Scanner;

/**
 * Encapsulates the UI of the chat bot.
 */
public class Ui {
    private final String FAREWELL = "Bye. Hope to see you again soon!";
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }
    public String getNextLine() {
        return scanner.nextLine();
    }
    public void close() {
        System.out.println(FAREWELL);
        this.scanner.close();
    }
    public void open() {
        this.scanner.close();
    }
    public void print(String s) {
        System.out.println(s);
    }
}
