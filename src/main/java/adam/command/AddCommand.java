package adam.command;

import adam.Storage;
import adam.Ui;
import adam.TaskList;
import adam.exception.DateException;
import adam.exception.NoDescriptionException;
import adam.exception.DeadlineException;
import adam.exception.EventsException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class is used to call the appropriate methods to add a Task to the list
 */
public class AddCommand implements Command {
    String[]tokens;
    String item;
    String input;

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

    /**
     * Calls the right add method for each kinds of Task while also checking for input errors.
     *
     * @param tasks Object that holds the list locally and has all the methods associated with it.
     * @param storage Storage used to store the list in the hard disk.
     * @param ui Ui that is used to print messages.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui){
        if (tokens.length == 1) {
            throw new NoDescriptionException();
        }
        switch (input) {
            case "todo":
                tasks.addTodo(item);
                break;
            case "deadline":
                String[] by = item.split(" /by ");
                if (by.length == 0) {
                    throw new NoDescriptionException();
                }
                if (by[0].equals("")) {
                    throw new NoDescriptionException();
                }
                if (by.length != 2) {
                    throw new DeadlineException();
                }
                try {
                    LocalDate.parse(by[1]);
                } catch (DateTimeParseException e) {
                    throw new DateException();
                }
                tasks.addDeadline(by[0], by[1]);
                break;
            case "event":
                String[] divide1 = item.split(" /from ");
                if (divide1.length == 0) {
                    throw new NoDescriptionException();
                }
                if (divide1[0].equals("")) {
                    throw new NoDescriptionException();
                }
                if (divide1.length != 2) {
                    throw new EventsException();
                }
                String text = divide1[0];
                String[] divide2 = divide1[1].split(" /to ");
                if (divide2.length != 2) {
                    throw new EventsException();
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
                tasks.addEvent(text, from, to);
                break;
            default:
                System.out.println("Wrong input");
        }
        tasks.save(storage);
    }
}
