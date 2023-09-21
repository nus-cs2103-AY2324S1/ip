package duke.command;

import java.util.regex.Pattern;

import duke.data.storage.Store;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
/**
 * Represents a command that interpret the user input string and mark a task as done.
 */
public class AddTagCommand implements Command {
    /**
     * Marks a task as done.
     * @param input String input from user in the format "mark {index}".
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("tag \\d+ \\w+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: tag <index> <tag>");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        s.addTagToTaskAtIndex(index, input.split(" ")[2]);
        return "Added tag to task: \n" + s.getTask(index).toString();
    }
}
