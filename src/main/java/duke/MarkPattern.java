package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is to store patterns to match against user input
 * */
public class MarkPattern {
    //Empty constructor to just initialise it
    public MarkPattern() {
    }

    //The pattern for mark
    public int mark(String args) {
        Pattern pattern = Pattern.compile("mark\\s*(\\d+)");
        Matcher matcher = pattern.matcher(args);

        if (matcher.find()) {
            String num = matcher.group(1);
            return Integer.parseInt(num) - 1;
        } else {
            return -1;
        }
    }

    //The pattern for unmark
    public int unmark(String args) {
        Pattern pattern = Pattern.compile("unmark\\s*(\\d+)");
        Matcher matcher = pattern.matcher(args);

        if (matcher.find()) {
            String num = matcher.group(1);
            return Integer.parseInt(num) - 1;
        } else {
            return -1;
        }
    }

    //The pattern for delete
    public int del(String args) {
        Pattern pattern = Pattern.compile("delete\\s*(\\d+)");
        Matcher matcher = pattern.matcher(args);

        if (matcher.find()) {
            String num = matcher.group(1);
            return Integer.parseInt(num) - 1;
        } else {
            return -1;
        }
    }
}
