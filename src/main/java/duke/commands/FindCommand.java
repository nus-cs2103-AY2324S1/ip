package duke.commands;

import duke.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FindCommand extends Command {
    private static final Pattern pattern = Pattern.compile("^find\\s+(?<keyword>.+)$");

    public FindCommand(String s) throws CommandException {
        super(s, pattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return String.join("\n", "Invalid format for command `find`!", "Usage: find <KEYWORD>");
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String keyword = matcher.group("keyword");

        if (keyword.isEmpty()) {
            throw new CommandException("Keyword cannot be empty!");
        }

        TaskList matchingTasks = tasks.filter(task -> task.hasKeyword(keyword));

        if (matchingTasks.isEmpty()) {
            return new CommandResult("You have no matching tasks in your list.");
        }

        List<String> response = new ArrayList<>();

        response.add("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            String taskEntry = String.format("%d. %s", i + 1, matchingTasks.get(i));
            response.add(taskEntry);
        }

        return new CommandResult(response);
    }
}
