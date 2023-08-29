package command;
import java.util.regex.Pattern;

import data.storage.Store;
import data.task.Task;
import exception.DukeException;
import exception.InvalidInputException;

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
