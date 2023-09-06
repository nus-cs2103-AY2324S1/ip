package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Ui` class is responsible for the interactions with the user.
 */
public class Ui {

    private static final String GREETING = "Hello! I'm JED, your personal chat-bot!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private static final String EMPTY_LIST = "Your list is currently empty.";
    private static final String ERROR_CANNOT_LOAD_DATA =
            "I am unable to read your duke.txt data file. Starting with a blank task list...";
    private static final String NO_MATCHING_TASKS = "Your keyword search returned no matching tasks.";
    private Scanner sc;

    /**
     * Constructs a `Ui` object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Retrieves the next line of input from the user and returns it.
     *
     * @return The next line of input.
     */
    public String getInput() {
        return this.sc.nextLine();
    }

    /**
     * Prints out the specified String, wrapped in a horizontal line break.
     *
     * @param str The string to be printed out.
     * @return The string to be printed out.
     */
    public String talk(String str) {
        String line = "_".repeat(50);
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
        return str;
    }

    /**
     * Prints out the greeting message.
     */
    public String greet() {
        return talk(GREETING);
    }

    /**
     * Prints out the goodbye message.
     */
    public String bye() {
        return talk(GOODBYE);
        // sc.close();
    }

    /**
     * Prints out the loading error message, used when Duke is unable to read the
     * input data file.
     */
    public String showLoadingError() {
        return talk(ERROR_CANNOT_LOAD_DATA);
    }

    /**
     * Prints out the specified ArrayList of items in the standard format, where
     * items are 1-indexed.
     *
     * @param items The ArrayList of items to be printed.
     */
    public String list(ArrayList<?> items) {
        int count = items.size();
        if (count == 0) {
            return talk(EMPTY_LIST);
        } else {
            String list = "";
            for (int i = 0; i < count; i++) {
                list += "  " + (i + 1) + ". " + items.get(i) + "\n";
            }
            return talk(list);
        }

    }

    /**
     * Prints out the specified ArrayList of searched items in the standard format,
     * where items are 1-indexed.
     *
     * @param items The ArrayList of searched items to be printed.
     */
    public String listSearch(ArrayList<?> items) {
        int count = items.size();
        if (count == 0) {
            return talk(NO_MATCHING_TASKS);
        } else {
            String list = "";
            for (int i = 0; i < count; i++) {
                list += "  " + (i + 1) + ". " + items.get(i) + "\n";
            }
            return talk(list);
        }
    }

    /**
     * Prints out the success message upon marking an item.
     *
     * @param item The item that has been marked.
     */
    public String markItem(String item) {
        return talk("Nice! I've marked this task as done:\n  " + item);
    }

    /**
     * Prints out the success message upon un-marking an item.
     *
     * @param item The item that has been un-marked.
     */
    public String unmarkItem(String item) {
        return talk("OK, I've marked this task as not done yet:\n  " + item);
    }

    /**
     * Prints out the success message upon deleting an item.
     *
     * @param item The item that has been deleted.
     */
    public String deleteItem(String item, int count) {
        return talk("Noted. I've removed this task:\n " + item + "\n Now you have " + count + " tasks in your list");
    }

    /**
     * Prints out the success message upon adding an item.
     *
     * @param item The item that has been added.
     */
    public String addItem(String item, int count) {
        return talk("Got it. I've added this task:\n  " + item + "\n Now you have "
                + count + " tasks in your list.");
    }
}
