package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {

    /**
     * Prints a horizontal line that separates commands.
     */
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Converts LocalDate to String format.
     *
     * @param date LocalDate to be converted.
     * @return String representation of given date.
     */
    public static String outputDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts LocalDateTime to String format.
     *
     * @param date LocalDateTime to be converted.
     * @return String representation of given date.
     */
    public static String outputDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Outputs error when file failed to load.
     */
    public void showLoadingError() {
        System.out.println("Unable to read tasks file, creating a new one...");
    }

    /**
     * Outputs greeting when the bot starts.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Zac");
        System.out.println("What can I do for you?");
    }

}
