package duke.commands;

import duke.TaskList;
import duke.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    private static final Pattern pattern = Pattern.compile("^deadline\\s+(?<description>.*?)\\s+/by\\s+(?<by>.*)$");

    public DeadlineCommand(String s) throws CommandException {
        super(s, pattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return String.join(
                "\n",
                "Invalid format for command `deadline`!",
                "Usage: deadline <DESCRIPTION> /by <DUE_DATE>",
                "<DUE_DATE> should be of the format YYYY-MM-DDTHH:mm[:ss.sss]"
        );
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String description = matcher.group("description");
        String by = matcher.group("by");

        if (description.isEmpty()) {
            throw new CommandException("Deadline description cannot be empty!");
        }

        try {
            LocalDateTime dueDate = LocalDateTime.parse(by);

            Deadline deadline = new Deadline(description, dueDate);
            tasks.add(deadline);

            return new CommandResult(
                    true,
                    "Got it. I've added this task:",
                    deadline.toString(),
                    String.format(
                            "Now you have %d %s in the list.",
                            tasks.size(),
                            tasks.size() == 1 ? "task" : "tasks"
                    )
            );
        } catch (DateTimeParseException e) {
            throw new CommandException("Deadline due date is not a valid datetime!");
        }
    }
}
