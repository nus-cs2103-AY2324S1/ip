package adam.command;

import adam.Storage;
import adam.TaskList;
import adam.Ui;

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
        this.tokens = tokens;
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
        int length = tasks.getSize();
        String respond = "Something went wrong";
        switch (text) {
        case "mark":
            respond = tasks.markAsDone(tokens);
            assert tasks.getSize() == length : "The size of the task should stay the same";
            break;
        case "unmark":
            respond = tasks.unmarkAsDone(tokens);
            assert tasks.getSize() == length : "The size of the task should stay the same";
            break;
        case "delete":
            respond = tasks.deleteTask(tokens);
            assert tasks.getSize() == length - 1 : "The size of the task should decrease";
            break;
        case "tag":
            respond = tasks.tagTask(tokens);
            break;
        default:
            respond = "Wrong input";
        }
        tasks.save(storage);
        return respond;
    }
}
