package command;

import java.util.regex.Pattern;

import data.storage.Store;
import exception.DukeException;
import exception.InvalidInputException;

public class FindCommand implements Command {
    Store s = Store.getInstance();

    @Override
    public void execute(String input) throws DukeException {
        Pattern pattern = Pattern.compile("find .+");
        if (!pattern.matcher(input).matches()) {
            throw new InvalidInputException("expected format: find <keyword>");
        }
        String keyword = input.split(" ")[1];
        System.out.println("Here are the matching tasks in your list:");
        System.out.print(s.find(keyword));
    }  
}
