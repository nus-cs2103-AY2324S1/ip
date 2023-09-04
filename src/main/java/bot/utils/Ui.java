package bot.utils;

import java.util.Iterator;

/**
 * User interface class responsible for dealing with interactions with the user.
 */
public class Ui {

    /**
     * Default constructor. Creates a Ui object.
     */
    public Ui() {
    }

    /**
     * Formats the input string as an error message for the bot.
     *
     * @param str Input string.
     * @return Input string formatted as an error message.
     */
    public String showError(String str) {
        return println("ERROR: " + str);
    }

    /**
     * Formats the input string similar to System.out.println().
     *
     * @param str Input string.
     * @return String with line separator.
     */
    public String println(String str) {
        return str + System.lineSeparator();
    }

    /**
     * Gets the bot's welcome message.
     *
     * @return Welcome message.
     */
    public String showWelcome() {
        return println("Hello! I'm the trash gremlin Caelus!")
                .concat(println("What can I do for you?"));
    }

    /**
     * Gets the bot's farewell message.
     *
     * @return Farewell message.
     */
    public String showGoodbye() {
        return println("Bye. I'll be at the nearest trash can!");
    }

    /**
     * Gets the inputted task list in a string format for printing to a screen.
     *
     * @param tasks Input task list.
     * @return Task list in string format.
     */
    public String displayTaskList(TaskList tasks) {
        StringBuilder out = new StringBuilder();
        Iterator<Task> iter = tasks.iterator();
        for (int ctr = 1; iter.hasNext(); ctr++) {
            out.append(ctr).append(". ")
                    .append(iter.next().toString())
                    .append(System.lineSeparator());
        }
        out.deleteCharAt(out.length() - 1);
        return println(out.toString());
    }
}
