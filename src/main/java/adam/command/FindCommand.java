package adam.command;

import adam.Storage;
import adam.TaskList;
import adam.Ui;
import adam.exception.DescriptionException;
import adam.exception.FindException;

/**
 * This class is used to call the find method if the user input is correct.
 */
public class FindCommand implements Command{
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
    public  FindCommand(String[]tokens, String item, String input) {
        this.tokens = tokens;
        this.item = item;
        this.input = input;
    }

    /**
     * Calls the find method while also checking for input errors.
     *
     * @param tasks Object that holds the list locally and has all the methods associated with it.
     * @param storage Storage used to store the list in the hard disk.
     * @param ui Ui that is used to print messages.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tokens.length == 1) {
            throw new FindException();
        }
        switch (input) {
        case "find":
            tasks.find(item);
            break;
        default:
            System.out.println("Wrong Input");
        }
    }
}
