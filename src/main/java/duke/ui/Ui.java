package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Ui` class is responsible for the interactions with the user.
 */
public class Ui {

    private final String GREETING = "Hello! I'm JED, your personal chat-bot!\nWhat can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again soon!";

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
     */
    public void talk(String str) {
        String line = "_".repeat(50);
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
    }

    /**
     * Prints out the greeting message.
     */
    public void greet() {
        talk(GREETING);
    }

    /**
     * Prints out the goodbye message.
     */
    public void bye() {
        talk(GOODBYE);
        sc.close();
    }

    /**
     * Prints out the loading error message, used when Duke is unable to read the
     * input data file.
     */
    public void showLoadingError() {
        talk("I am unable to read your duke.txt data file. Starting with a blank task list...");
    }

    /**
     * Prints out the specified ArrayList of items in the standard format, where
     * items are 1-indexed.
     *
     * @param items The ArrayList of items to be printed.
     */
    public void list(ArrayList<?> items) {
        int count = items.size();
        if (count == 0) {
            talk("Your list is currently empty.");
        } else {
            String list = "";
            for (int i = 0; i < count; i++) {
                list += "  " + (i + 1) + ". " + items.get(i) + "\n";
            }
            talk(list);
        }
    }

    /**
     * Prints out the specified ArrayList of searched items in the standard format,
     * where items are 1-indexed.
     *
     * @param items The ArrayList of searched items to be printed.
     */
    public void listSearch(ArrayList<?> items) {
        int count = items.size();
        if (count == 0) {
            talk("Your keyword search returned no matching tasks.");
        } else {
            String list = "";
            for (int i = 0; i < count; i++) {
                list += "  " + (i + 1) + ". " + items.get(i) + "\n";
            }
            talk(list);
        }
    }

    /**
     * Prints out the success message upon marking an item.
     *
     * @param item The item that has been marked.
     */
    public void markItem(String item) {
        talk("Nice! I've marked this task as done:\n  " + item);
    }

    /**
     * Prints out the success message upon un-marking an item.
     *
     * @param item The item that has been un-marked.
     */
    public void unmarkItem(String item) {
        talk("OK, I've marked this task as not done yet:\n  " + item);
    }

    /**
     * Prints out the success message upon deleting an item.
     *
     * @param item The item that has been deleted.
     */
    public void deleteItem(String item, int count) {
        talk("Noted. I've removed this task:\n " + item + "\n Now you have " + count + " tasks in your list");
    }

    /**
     * Prints out the success message upon adding an item.
     *
     * @param item The item that has been added.
     */
    public void addItem(String item, int count) {
        talk("Got it. I've added this task:\n  " + item + "\n Now you have "
                + count + " tasks in your list.");
    }
}
