package adam.command;

import adam.Storage;
import adam.TaskList;
import adam.Ui;
import adam.exception.DateException;
import adam.exception.DeadlineException;
import adam.exception.DescriptionException;
import adam.exception.EventException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class is used to call the appropriate methods to add a Task to the list
 */
public class AddCommand implements Command {
    private String[] tokens;
    private String item;
    private String input;

    /**
     * Initializes the string from the user input that has been processed.
     *
     * @param tokens Array of string divided by empty space.
     * @param item String after the command.
     * @param input String of the command.
     */
    public AddCommand(String[]tokens, String item, String input) {
        this.tokens = tokens;
        this.item = item;
        this.input = input;
    }

    public String checkDeadline(TaskList tasks) {
        String[] by = item.split(" /by ");
        if (by.length == 0) {
            throw new DescriptionException();
        }
        if (by[0].equals("")) {
            throw new DescriptionException();
        }
        if (by.length != 2) {
            throw new DeadlineException();
        }
        try {
            LocalDate.parse(by[1]);
        } catch (DateTimeParseException e) {
            throw new DateException();
        }
        return  tasks.addDeadline(by[0], by[1]);
    }

    public String checkEvent(TaskList tasks) {
        String[] divide1 = item.split(" /from ");
        if (divide1.length == 0) {
            throw new DescriptionException();
        }
        if (divide1[0].equals("")) {
            throw new DescriptionException();
        }
        if (divide1.length != 2) {
            throw new EventException();
        }
        String text = divide1[0];
        String[] divide2 = divide1[1].split(" /to ");
        if (divide2.length != 2) {
            throw new EventException();
        }
        String from = divide2[0];
        String to = divide2[1];
        try {
            LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            throw new DateException();
        }
        try {
            LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DateException();
        }
        return tasks.addEvent(text, from, to);
    }
    /**
     * Calls the right add method for each kinds of Task while also checking for input errors.
     *
     * @param tasks Object that holds the list locally and has all the methods associated with it.
     * @param storage Storage used to store the list in the hard disk.
     * @param ui Ui that is used to print messages.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui){
        String respond = "Something went wrong";
        if (tokens.length == 1) {
            throw new DescriptionException();
        }
        switch (input) {
        case "todo":
            respond = tasks.addTodo(item);
            break;
        case "deadline":
            respond = checkDeadline(tasks);
            break;
        case "event":
            respond = checkEvent(tasks);
            break;
        default:
            respond = "Wrong input";
        }
        tasks.save(storage);
        return respond;
    }
}
