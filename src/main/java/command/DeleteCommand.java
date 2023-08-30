package command;
import java.util.regex.Pattern;

import data.storage.Store;
import data.task.Task;
import exception.DukeException;
import exception.InvalidInputException;



/**
 * Deletes a task from the task list.
 * @param input String input from user in the format "delete <index>".
 * @throws DukeException If input is invalid.
 */

public class DeleteCommand implements Command {

    @Override
    public void execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("delete \\d+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: delete <index>");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        if (s.hasTaskAtIndex(index)) {
            Task t = s.getTask(index);
             System.out.println("Noted. I've removed this task:");
             System.out.println(t);  
        } 
        s.deleteTask(index);
    }  
}
