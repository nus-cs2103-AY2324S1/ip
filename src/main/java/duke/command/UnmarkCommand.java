package duke.command;

import java.util.regex.Pattern;

import duke.data.storage.Store;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

   
public class UnmarkCommand implements Command {
     /**
     * Marks a task as not done.
     * @param input String input from user in the format "unmark <index>".
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("unmark \\d+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: unmark <index>");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        s.unmark(index);
        return "Nice! I've marked this task as not done:\n" + s.getTask(index);
    }

}
