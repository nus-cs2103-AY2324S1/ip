package adam.command;

import adam.Storage;
import adam.TaskList;
import adam.Ui;
import adam.exception.NoDescriptionException;

/**
 * This class is used to call the find method if the user input is correct.
 */
public class FindCommand implements Command{
    private String[] tokens;
    private String item;
    private String input;

    public  FindCommand(String[]tokens, String item, String input) {
        this.tokens = tokens;
        this.item = item;
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tokens.length == 1) {
            throw new NoDescriptionException();
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
