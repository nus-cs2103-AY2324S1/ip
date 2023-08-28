package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static String outputDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public void showLoadingError() {
        System.out.println("Unable to read tasks file, creating a new one...");
    }

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
