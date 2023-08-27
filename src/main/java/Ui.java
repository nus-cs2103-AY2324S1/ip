import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    void showLine() {
        out.println("____________________________________________________________");
    }

    void showWelcome() {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Rua, your ChatBot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        out.println(greeting);
    }

    void showGoodbye() {
        String goodbye= " Bye. Hope to see you again soon!";
        out.println(goodbye);
    }

    void showMessage(String str) {
        out.println(str);
    }

    void showError(String errorMessage) {
        out.println("You get an error: " + errorMessage);
    }

    void showLoadingError() {
        showError("Given tasklist cannot be loaded. Now creating a new tasklist instead.");
    }

    String readCommand() {
        return in.nextLine();
    }
}
