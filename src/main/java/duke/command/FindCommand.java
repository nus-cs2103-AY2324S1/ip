package duke.command;

import java.util.regex.Pattern;

import duke.data.storage.Store;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class FindCommand implements Command {
    Store s = Store.getInstance();

    /**
     * Finds tasks that contain the keyword.
     * @param input String input from user in the format "find <keyword>".
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("find .+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: find <keyword>");
        }
        String keyword = input.split(" ")[1];
        return "Here are the matching tasks in your list:\n" + s.find(keyword);
    }  
}
