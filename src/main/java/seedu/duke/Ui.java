package seedu.duke;

import java.util.Scanner;

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
    private Scanner scanner;
    private String input;
    private Parser parser;

    /**
     * The main constructor of this Ui class.
     *
     * @param name Name of this chatbot.
     */
    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
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
    public void greet() {
        Ui.line();
        System.out.println(Ui.I5 + "Hello! I'm " + this.name);
        System.out.println(Ui.I5 + "What can I do for you?");
        Ui.line();
    }

    /**
     * Says bye to the user.
     */
    public void exit() {
        System.out.println(Ui.I5 + "Bye. Hope to see you again soon!");
    }

    /**
     * Terminates this chatbot.
     */
    public void bye() {
        this.scanner.close();
        this.exit();
        Ui.line();
    }

    /**
     * Gets the next user input.
     *
     * @return A Commands enum after parsing the user input string.
     */
    public Commands getNextUserInput() {
        this.input = this.scanner.nextLine();
        return this.parser.parse(this.input);
    }

    /**
     * Marks a task as completed.
     *
     * @return The index of the task to be marked.
     * @throws DukeException If there are any user input errors.
     */
    public int mark() throws DukeException {
        return this.parser.mark(this.input);
    }

    /**
     * Marks a task as not completed.
     *
     * @return The index of the task to be unmarked.
     * @throws DukeException If there are any user input errors.
     */
    public int unmark() throws DukeException {
        return this.parser.unmark(this.input);
    }

    /**
     * Deletes a task.
     *
     * @return The index of the task to be deleted.
     * @throws DukeException If there are any user input errors.
     */
    public int delete() throws DukeException {
        return this.parser.delete(this.input);
    }

    /**
     * Instantiates a Todo task.
     *
     * @return A Todo task.
     * @throws DukeException If there are user input errors.
     */
    public Task todo() throws DukeException {
        return this.parser.todo(this.input);
    }

    /**
     * Instantiates a Deadline task.
     *
     * @return A Deadline task.
     * @throws DukeException If there are user input errors.
     */
    public Task deadline() throws DukeException {
        return this.parser.deadline(this.input);
    }

    /**
     * Instantiates an Event task.
     *
     * @return An Event task.
     * @throws DukeException If there are user input errors.
     */
    public Task event() throws DukeException {
        return this.parser.event(this.input);
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
    public String find() throws DukeException {
        return this.parser.find(this.input);
    }
}
