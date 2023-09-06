package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses all input commands given by the user.
 */
public class Parser {

    //The DukeList to store all tasks given to the Duke bot.
    private DukeList list;

    //The timeParser to parse and format all times given.
    private static DateAndTime timeParser = new DateAndTime();


    /**
     * Instantiates a new Parser class
     * @param list the DukeList that contains all tasks at hand.
     */
    public Parser(DukeList list) {
        this.list = list;
    }

    /**
     * Helps to parse and manage the user's inputs.
     * @param tally takes in the input string.
     */
    public void messageHandler(String tally) {
        String comd = tally.split(" ")[0];

        switch (comd) {
        case "list":
            list.display();
            break;
        case "search":
            Pattern searchCommand = Pattern.compile("search (.+)");
            Matcher ms = searchCommand.matcher(tally);
            if (this.groupRun(ms, 1)) {
                list.search(ms.group(1));
            }
            break;
        case "todo":
            Pattern todoCommand = Pattern.compile("todo (.+)");
            Matcher mt = todoCommand.matcher(tally);
            if (this.groupRun(mt, 1)) {
                list.store(mt.group(1));
            } else {
                UI.inputErrorMessage("todo");
            }
            break;
        case "deadline":
            Pattern deadlineCommand = Pattern.compile("deadline (.+) /by (.+)");
            Matcher md = deadlineCommand.matcher(tally);
            if (this.groupRun(md, 2)) {
                String formattedDeadline = this.timeParse(md.group(2));
                if (formattedDeadline != null) {
                    list.store(md.group(1), formattedDeadline);
                } else {
                    UI.inputErrorMessage("deadline");
                }
            } else {
                UI.inputErrorMessage("deadline");
            }
            break;
        case "event":
            Pattern eventCommand = Pattern.compile("event (.+) /from (.+) /to (.+)");
            Matcher ml = eventCommand.matcher(tally);
            if (this.groupRun(ml, 3)) {
                if (this.isValidTime(ml.group(2), ml.group(3))) {
                    String formattedStart = this.timeParse(ml.group(2));
                    String formattedEnd = this.timeParse(ml.group(3));
                    if (formattedStart != null && formattedEnd != null) {
                        list.store(ml.group(1), formattedStart, formattedEnd);
                    }
                } else {
                    UI.inputErrorMessage("event");
                }
            } else {
                UI.inputErrorMessage("event");
            }
            break;
        case "mark":
            try {
                String indexMark = tally.split(" ")[1];
                list.mark(Integer.parseInt(indexMark));
            } catch (NullPointerException e) {
                UI.inputErrorMessage("mark");
            }
            break;
        case "unmark":
            try {
                String indexunmark = tally.split(" ")[1];
                list.unmark(Integer.parseInt(indexunmark));
            } catch (NullPointerException e) {
                UI.inputErrorMessage("unmark");
            }
            break;
        case "delete":
            try {
                String indexUnmark = tally.split(" ")[1];
                list.delete(Integer.parseInt(indexUnmark));
            } catch (NullPointerException e) {
                UI.inputErrorMessage("delete");
            }
            break;
        case "bye":
            UI.bye();
            break;
        default:
            UI.inputErrorMessage("other");
        }

    }

    /**
     * Recursively runs the matches() method on a given matcher n times.
     * @param m The matcher object
     * @param n number of times it has to run
     * @return the boolean value of all matches() run recursively.
     */
    private boolean groupRun(Matcher m, int n) {
        return n == 0 ? false : n == 1 ? m.matches() : m.matches() && groupRun(m, n - 1);
    }

    /**
     * Saves and shuts down the current date in the DukeList.
     */
    public void close() {
        this.list.save();
    }

    /**
     * Parses the string date into a proper date format.
     * @param date the date entered by the user.
     * @return The formatted date string.
     */
    public String timeParse(String date) {
        String[] dateInfo = date.split(" ");

        if (dateInfo.length == 1) {
            return timeParser.dayParse(dateInfo[0]);
        } else if (dateInfo.length == 2) {
            return timeParser.dayParse(dateInfo[0], dateInfo[1]);
        } else {
            return null;
        }
    }

    /**
     * Checks if a given start and end date for event tasks are valid
     * @param start Starting date and time, if any.
     * @param end Ending date and time, if any.
     * @return Whether the date range is valid.
     */
    public boolean isValidTime(String start, String end) {
        String[] startInfo = start.split(" ");

        String[] endInfo = end.split(" ");

        if (startInfo.length == 1 || endInfo.length == 1) {
            return timeParser.isValidDate(startInfo[0], endInfo[0]);
        } else if (startInfo.length == 2 && endInfo.length == 2){
            return timeParser.isValidDate(startInfo[0], startInfo[1], endInfo[0], endInfo[1]);
        } else {
            return false;
        }
    }
}
