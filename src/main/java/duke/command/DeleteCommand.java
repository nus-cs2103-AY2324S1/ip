package duke.command;

import java.util.regex.Pattern;

import duke.data.storage.Store;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;



/**
 * Deletes a task from the task list.
 * @param input String input from user in the format "delete <index>".
 * @throws DukeException If input is invalid.
 */

public class DeleteCommand implements Command {

    @Override
    public String execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("delete \\d+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: delete <index>");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        
        if (s.hasTaskAtIndex(index)) {
            Task t = s.getTask(index);
            s.deleteTask(index);
            return "Noted. I've removed this task:\n" + t;
        } else {
            throw new InvalidInputException("index out of bounds");
        } 
    }
}
