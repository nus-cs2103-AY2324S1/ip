import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

/**
 * Duke is entry point of our program
 */
public class Duke {
    static final String LISTPATH = "./data/list.txt";

    private Storage storage;

    public Duke(String filepath) {
        this.storage = new Storage();
    }

    public void run() {
        Greeting.greet();
        Commands.Run(this.storage);
    }


    public static void main(String[] args) {

        new Duke(LISTPATH).run();
    }
}
