package command;

import java.util.regex.Pattern;

import data.storage.Store;
import exception.DukeException;
import exception.InvalidInputException;
public class Unmark implements Command{

    /**
     * Marks a task as not done.
     * @param input String input from user in the format "unmark <index>".
     * @throws DukeException If input is invalid.
     */
    @Override
    public void execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("unmark \\d+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: unmark <index>");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        s.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(s.getTask(index));
    }

}
