package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.task.Task;

/**
 * Represents the UI used by this chatbot.
 * A UI is responsible for the managing the
 * interactions between users and the chatbot.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Ui {
    public static final String I4 = "    ";
    public static final String I5 = Ui.I4 + " ";
    public static final String I7 = Ui.I5 + "  ";
    public static final String LINE = Ui.I4 + "——————————————————————————————————————————————————————————————————";

    private String name;
    private Parser parser;

    /**
     * The main constructor of this Ui class.
     *
     * @param name Name of this chatbot.
     */
    public Ui(String name) {
        this.name = name;
        this.parser = new Parser();
    }

    /**
     * Prints out a horizontal line.
     */
    public static void line() {
        System.out.println(Ui.LINE);
    }

    /**
     * Greets the user.
     */
    public static String greet() {
        return "Hello! I'm " + "DUKE_MASTER" + "\n"
                + "What can I do for you?";
    }

    /**
     * Says bye to the user.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public Command parseUserInput(String input) {
        return this.parser.parse(input);
    }

    /**
     * Marks a task as completed.
     *
     * @return The index of the task to be marked.
     * @throws DukeException If there are any user input errors.
     */
    public int mark(String input) throws DukeException {
        return this.parser.mark(input);
    }

    /**
     * Marks a task as not completed.
     *
     * @return The index of the task to be unmarked.
     * @throws DukeException If there are any user input errors.
     */
    public int unmark(String input) throws DukeException {
        return this.parser.unmark(input);
    }

    /**
     * Deletes a task.
     *
     * @return The index of the task to be deleted.
     * @throws DukeException If there are any user input errors.
     */
    public int delete(String input) throws DukeException {
        return this.parser.delete(input);
    }

    /**
     * Instantiates a Todo task.
     *
     * @return A Todo task.
     * @throws DukeException If there are user input errors.
     */
    public Task todo(String input) throws DukeException {
        return this.parser.todo(input);
    }

    /**
     * Instantiates a Deadline task.
     *
     * @return A Deadline task.
     * @throws DukeException If there are user input errors.
     */
    public Task deadline(String input) throws DukeException {
        return this.parser.deadline(input);
    }

    /**
     * Instantiates an Event task.
     *
     * @return An Event task.
     * @throws DukeException If there are user input errors.
     */
    public Task event(String input) throws DukeException {
        return this.parser.event(input);
    }

    /**
     * Returns the String representation
     * of the task which the user intends
     * to find.
     *
     * @return The String representation
     *      of the task which the user intends
     *      to find.
     * @throws DukeException If there are user input errors.
     */
    public String find(String input) throws DukeException {
        return this.parser.find(input);
    }
}
