package adam.command;

import adam.Storage;
import adam.Ui;
import adam.TaskList;
import adam.exception.OneWordException;
/**
 * This class is used to call either the list or bye methods
 */
public class SingleCommand implements Command {
    private String[] tokens;
    private String input;

    /**
     * Initializes the string from the user input that has been processed.
     *
     * @param tokens Array of string divided by empty space.
     * @param input The specific command from the parser.
     */
    public SingleCommand(String[] tokens, String input) {
        this.tokens = tokens;
        this.input = input;
    }

    /**
     * Calls the list or bye method while also checking for input errors.
     *
     * @param tasks Object that holds the list locally and has all the methods associated with it.
     * @param storage Storage used to store the list in the hard disk.
     * @param ui Ui that is used to print messages.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui){
        if (tokens.length > 1) {
            throw new OneWordException();
        }
        switch (input) {
        case "bye":
            tasks.bye();
            break;
        case "list":
            tasks.list();
            break;
        default:
            System.out.println("Wrong input");
        }
    }
}
