package command;

import java.util.regex.Pattern;

import data.storage.Store;
import exception.DukeException;
import exception.InvalidInputException;

public class MarkCommand implements Command{
    /**
     * Marks a task as done.
     * @param input String input from user in the format "mark <index>".
     * @throws DukeException If input is invalid.
     */
    @Override
    public void execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("mark \\d+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: mark <index>");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        s.mark(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(s.getTask(index));
    }

}