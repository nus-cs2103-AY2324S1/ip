package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * one of the Tasks that user's can add into their list
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor to initialise a Deadline object
     * @param description the Task description that is obtained from the Task class
     * @param by the deadline time component that is stored as a LocalDateTime
     */
    private Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + localDatetoString(by) + ")";
    }
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "| " + localDatetoString(by);
    }

    /**
     * A method to convert the LocalDateTime to a String in "MMM d yyyy h:mma" format
     * @param dateTime the stored LocalDateTime
     * @return a String
     */
    public static String localDatetoString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String formattedDateTime = dateTime.format(outputFormatter);
        return formattedDateTime;
    }

    /**
     * A constructor that takes in a String time component and returns a Deadline with LocalDateTime field
     * @param description String description
     * @param timeComponent Time component of the Deadline in a form of a String
     * @return Deadline object
     * @throws DukeException if invalid input for timeComponent
     */
    public static Deadline newDeadline(String description, String timeComponent) throws DukeException {
        return new Deadline(description, convertToLocalDateTime(timeComponent));
    }
    /**
     * A function that takes in the by part of a duke.Deadline duke.Task, and converts it to a LocalDateTime
     * For example, the input 'Sunday 1700' will return the corresponding LocalDateTime
     *
     * @param string the by part of the duke.Deadline duke.Task
     * @return the LocalDateTime corresponding to the duke.Deadline
     * @throws DukeException if a specific time in 24hr format is not put
     */
    public static LocalDateTime convertToLocalDateTime(String string) throws DukeException {
        if (string.indexOf('/') != -1) {
            // inputs of form "Date/Month/Year hhmm", e.g. "20/9/2023 1840"
            if (string.lastIndexOf('/') + 5 >= string.length()) {
                // if "hhmm" is not present
                throw new DukeException("put in a time pls");
            }
            LocalDateTime dateTime = parseDateTime(string, "/");
            return dateTime;
        } else if (string.indexOf('-') != -1) { //
            // inputs of form "Date-Month-Year hhmm", e.g. "20-9-2023 1840"
            if (string.lastIndexOf('-') + 3 >= string.length()) {
                // if "hhmm" is not present
                throw new DukeException("put in a time pls");
            }
            LocalDateTime dateTime = parseDateTime(string, "-");
            return dateTime;
        } else { // "Mon 1800"
            // problem 1: it goes backwards in time in certain cases
            LocalDateTime dateTime = parseDateTime(string, "NIL");
            return dateTime;
        }
    }
    /**
     * A function that helps convert a string to a LocalDateTime
     *
     * @param userInput the by part of the duke.Deadline duke.Task, e.g. "2/12/2019 1800"
     * @param c whether the duke.Deadline is put in a '-' format or '/' format
     * @return a LocalDateTime
     * @throws DukeException if a specific time in 24hr format is not put
     */
    public static LocalDateTime parseDateTime(String userInput, String c) throws DukeException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new DukeException("Put in a valid time input!");
        }

        String datePart = parts[0];
        String timePart = parts[1];

        String[] dateComponents;
        if (c.equals("NIL")) {
            int year = LocalDate.now().getYear();
            int month = LocalDate.now().getMonth().getValue();
            int daysToAdd = -LocalDateTime.now().getDayOfWeek().compareTo(getDayOfWeek(datePart.toUpperCase()));
            // compares today's DAY with the DAY in userInput, and returns the number of days to add

            int hour = Integer.parseInt(timePart.substring(0, 2));
            int minute = Integer.parseInt(timePart.substring(2, 4));

            LocalDate temp = LocalDate.of(year, month, 1);
            int maxDaysOfMonth = temp.lengthOfMonth();
            // represents the maximum no. of days in that month

            int date = LocalDate.now().getDayOfMonth() + daysToAdd;
            if (date > maxDaysOfMonth) {
                // Date overflows, adjust LocalDateTime to the next month
                return LocalDateTime.of(year, month + 1, date - maxDaysOfMonth, hour, minute);
            } else {
                return LocalDateTime.of(year, month, date, hour, minute);
            }
        } else if (c.equals("/")) {
            dateComponents = datePart.split("/");
        } else {
            assert c.equals("-") : "something wrong here!";
            dateComponents = datePart.split("-");
        }

        if (dateComponents.length != 3) {
            throw new IllegalArgumentException("Invalid date format");
        }

        int date = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);
        int hour = Integer.parseInt(timePart.substring(0, 2));
        int minute = Integer.parseInt(timePart.substring(2, 4));

        return LocalDateTime.of(year, month, date, hour, minute);
    }

    /**
     * A function that takes in a user input that is the day of the week and returns the
     * corresponding DayOfWeek
     *
     * @param string the user input that is a day of the week, e.g. "sun", "Tuesday", "Mon"
     * @return the DayOfWeek as an enum
     */
    public static DayOfWeek getDayOfWeek(String string) throws DukeException {
        DayOfWeek result;
        String day = string.substring(0, 3);
        switch(day) {
        case "MON":
            result = DayOfWeek.MONDAY;
            break;
        case "TUE":
            result = DayOfWeek.TUESDAY;
            break;
        case "WED":
            result = DayOfWeek.WEDNESDAY;
            break;
        case "THU":
            result = DayOfWeek.THURSDAY;
            break;
        case "FRI":
            result = DayOfWeek.FRIDAY;
            break;
        case "SAT":
            result = DayOfWeek.SATURDAY;
            break;
        case "SUN":
            result = DayOfWeek.SUNDAY;
            break;
        default:
            throw new DukeException("Only inputs such as 'Mon', 'Tuesday', 'sat' allowed!");
        }
        return result;
    }
}
