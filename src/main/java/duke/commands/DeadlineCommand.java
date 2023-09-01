package duke.commands;

import duke.TaskList;
import duke.tasks.Deadline;

import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    private static final Pattern pattern = Pattern.compile("^deadline\\s+(?<description>.*?)\\s+/by\\s+(?<by>.*)$");

    public DeadlineCommand(String s) throws CommandException {
        super(s, pattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return String.join("\n", "Invalid format for command `deadline`!", "Usage: deadline <DESCRIPTION> /by <DEADLINE>");
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String description = matcher.group("description");
        String by = matcher.group("by");

        if (description.isEmpty()) {
            throw new CommandException("Deadline description cannot be empty!");
        }

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        return new CommandResult(true, "Got it. I've added this task:", deadline.toString(), String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }
}
