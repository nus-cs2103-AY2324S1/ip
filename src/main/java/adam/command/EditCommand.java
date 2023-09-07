package adam.command;

import adam.Storage;
import adam.Ui;
import adam.TaskList;
import adam.exception.NumberException;
import adam.exception.OutOfBoundException;

/**
 * This class is used to call the appropriate methods to edit a Task inside the list
 */
public class EditCommand implements Command {
    private String[] tokens;
    private String text;


    /**
     * Initializes the string from the user input that has been processed.
     *
     * @param tokens Array of string divided by empty space.
     * @param text The specific command from the parser.
     */
    public EditCommand(String[] tokens, String text) {
        this.tokens =  tokens;
        this.text = text;
    }

    /**
     * Calls the right method to edit Tasks inside the list while also checking for input errors.
     *
     * @param tasks Object that holds the list locally and has all the methods associated with it.
     * @param storage Storage used to store the list in the hard disk.
     * @param ui Ui that is used to print messages.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String respond = "Something went wrong";
        if (tokens.length != 2) {
            throw new NumberException();
        }
        if (!tokens[1].matches("[0-9]+")) {
            throw new NumberException();
        }
        int number = Integer.valueOf(tokens[1]);
        if (number > tasks.getSize()) {
            throw new OutOfBoundException();
        }
        switch (text) {
        case "mark":
            respond = tasks.markAsDone(number);
            break;
        case "unmark":
            respond = tasks.unmarkAsDone(number);
            break;
        case "delete":
            respond = tasks.deleteTask(number);
            break;
        default:
            respond = "Wrong input";
        }
        tasks.save(storage);
        return respond;
    }
}
