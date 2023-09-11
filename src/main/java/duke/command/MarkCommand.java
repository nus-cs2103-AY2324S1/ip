package duke.command;

import java.util.regex.Pattern;

import duke.data.storage.Store;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class MarkCommand implements Command{
    /**
     * Marks a task as done.
     * @param input String input from user in the format "mark <index>".
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("mark \\d+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: mark <index>");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        s.mark(index);
        return "Nice! I've marked this task as done:\n" + s.getTask(index);
    }

}