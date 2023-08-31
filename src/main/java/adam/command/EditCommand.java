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
    public void execute(TaskList tasks, Storage storage, Ui ui) {
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
            tasks.markAsDone(number);
            break;
        case "unmark":
            tasks.unmarkAsDone(number);
            break;
        case "delete":
            tasks.deleteTask(number);
            break;
        default:
            System.out.println("Wrong input");
        }
        tasks.save(storage);
    }
}
