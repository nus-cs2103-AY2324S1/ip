package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses all input commands given by the user.
 */
public class Parser {

    //The DukeList to store all tasks given to the Duke bot.
    private TaskList list;

    //The timeParser to parse and format all times given.
    private DateAndTime timeParser = new DateAndTime();


    /**
     * Instantiates a new Parser class
     * @param list the DukeList that contains all tasks at hand.
     */
    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Helps to parse and manage the user's inputs.
     *
     * @param tally takes in the input string.
     */
    public Command messageHandler(String tally) {
        String comd = tally.split(" ")[0];
        Command command = null;

        switch (comd) {
        case "list":
            command = new TaskLister(this.list);
            break;
        case "search":
            Pattern searchCmd = Pattern.compile("search (.+)");
            Matcher ms = searchCmd.matcher(tally);
            command = this.groupRun(ms, 1) ? new TaskSearcher(ms.group(1), list)
                : new TaskError("search");
            break;
        case "todo":
            Pattern todoCmd = Pattern.compile("todo (.+)");
            Matcher mt = todoCmd.matcher(tally);
            command = this.groupRun(mt, 1) ? new ToDoAdder(mt.group(1), list)
                : new TaskError("todo");
            break;
        case "deadline":
            Pattern deadlineCmd = Pattern.compile("deadline (.+) /by (.+)");
            Matcher md = deadlineCmd.matcher(tally);
            if (this.groupRun(md, 2)) {
                String formattedDeadline = this.timeParse(md.group(2));
                command = formattedDeadline.equals(md.group(2)) ? new DeadlineAdder(md.group(1), formattedDeadline, list)
                        : new TaskError("deadline");
            } else {
                command = new TaskError("deadline");
            }
            break;
        case "event":
            Pattern eventCmd = Pattern.compile("event (.+) /from (.+) /to (.+)");
            Matcher ml = eventCmd.matcher(tally);
            if (this.groupRun(ml, 3)) {
                if (this.isValidTime(ml.group(2), ml.group(3))) {
                    String formattedStart = this.timeParse(ml.group(2));
                    String formattedEnd = this.timeParse(ml.group(3));
                    command = new EventAdder(ml.group(1), formattedStart, formattedEnd, list);
                } else {
                    command = new TaskError("event");
                }
            } else {
                command = new TaskError("event");
            }
            break;
        case "mark":
            try {
                String indexMark = tally.split(" ")[1];
                command = new TaskMarker(Integer.parseInt(indexMark), list);
            } catch (NullPointerException e) {
                command = new TaskError("mark");
            }
            break;
        case "unmark":
            try {
                String indexMark = tally.split(" ")[1];
                command = new TaskUnmarker(Integer.parseInt(indexMark), list);
            } catch (NullPointerException e) {
                command = new TaskError("unmark");
            }
            break;
        case "delete":
            try {
                String indexMark = tally.split(" ")[1];
                command = new TaskDeleter(Integer.parseInt(indexMark), list);
            } catch (NullPointerException e) {
                command = new TaskError("delete");
            }
            break;
        case "bye":
            command = new Bye(list);
            break;
        default:
            command = new TaskError("other");
        }

        return command;
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
     * Parses the string date into a proper date format.
     * @param date the date entered by the user.
     * @return The formatted date string.
     */
    public String timeParse(String date) {
        return timeParser.dayParse(date, "YYYY-MM-DD HH:MM");
    }

    /**
     * Checks if a given start and end date for event tasks are valid
     * @param start Starting date and time, if any.
     * @param end Ending date and time, if any.
     * @return Whether the date range is valid.
     */
    public boolean isValidTime(String start, String end) {
        return timeParser.isValidDate(start, end);
    }
}