import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

/**
 * Duke is entry point of our program
 */
public class Duke {
    static final String LISTPATH = "./data/list.txt";
    
    public static void main(String[] args) {
        File f = new File(LISTPATH);

        Greeting.greet();
        Commands.Run(f);
    }
}
