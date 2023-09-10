package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Interface that reads in inputs and displays outputs.
 */
public abstract class Ui {

    protected Scanner sc;
    protected String msg;

    /**
     * Constructor for Ui.
     *
     * @param sc The scanner from which inputs are read.
     */
    public Ui(Scanner sc) {
        this.sc = sc;
        this.msg = "";
    }

    /**
     * Method to set display message.
     *
     * @param msg Message to be displayed.
     */
    public abstract void print(String msg);

    /**
     * Shared default message for counting tasks.
     *
     * @param n Number of tasks.
     * @return The default message for that number of tasks.
     */
    public static String getTaskCount(int n) {
        return String.format("You have %d task%s in the list now.", n, n == 1 ? "" : "s");
    }

    /**
     * Converts list to be displayed as a string.
     *
     * @param arr List converted.
     * @return The list as a string.
     */
    public static <T> String stringifyList(List<T> arr) {
        List<String> enumArr = new ArrayList<>();
        int i = 1;
        for (T e : arr) {
            enumArr.add(String.format("%d. %s", i++, e.toString()));
        }
        return String.join("\n", enumArr);
    }

    /**
     * Converts date to be displayed as a string.
     *
     * @param date Date converted.
     * @return The date as a string.
     */
    public static String stringifyDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * Returns next line read in.
     *
     * @return The line read in.
     */
    public String readCommand() {
        return sc.nextLine().replaceAll("\n", "").trim();
    }

    /**
     * Returns message to be displayed.
     *
     * @return Message to be displayed.
     */
    public String flush() {
        return this.msg;
    }

    /**
     * Returns whether or not there is more user input.
     *
     * @return Whether or not there is more user input.
     */
    public boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Stop monitoring input.
     */
    public void close() {
        sc.close();
    }

}
