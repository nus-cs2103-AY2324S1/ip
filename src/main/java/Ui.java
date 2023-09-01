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

    public void showWelcomeMessage() {
        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        out.println(logo);
    }

    public void showLoadingError(){
        out.println("Failed to load.");

    }
}
