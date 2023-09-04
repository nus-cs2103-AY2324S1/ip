package commands;

import client.Rock;
import io.Parser;

public class CommandTaskFind extends Command {
    public CommandTaskFind(Rock client) {
        super(client);
    }
    public String apply(Parser input) {
        String keyword = input.getDefaultString();
        return client.taskListFilteredSearch(task -> task.getName().contains(keyword));
    }
}
