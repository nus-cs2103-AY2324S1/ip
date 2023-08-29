import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

/**
 * Duke is entry point of our program
 */
public class Duke {
    static final String LISTPATH = "./data/list.txt";
    static final Pattern datepattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/(\\d{4}) (\\d{4}$)");
    static final Pattern dateStringPattern = Pattern.compile("\\d{1,2} (January|February|March|April|May|June|July|August|September|October|November|December) \\d{4} \\d{2}:\\d{2}");

    public static void main(String[] args) {
        File f = new File(LISTPATH);

        Greeting.greet();
        Commands.Run(f);
    }
}
