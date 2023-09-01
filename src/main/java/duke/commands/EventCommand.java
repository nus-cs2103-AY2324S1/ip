package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import duke.TaskList;
import duke.tasks.Event;

/**
 * A command to add a new event.
 */
public class EventCommand extends Command {
    private static final Pattern pattern = Pattern.compile(
            "^event\\s+(?<description>.*?)\\s+(?:/from\\s+(?<startFore>.*?)\\s+/to\\s+(?<endAft>.*)|/to\\s+"
                    + "(?<endFore>.*?)\\s+/from\\s+(?<startAft>.*))$");

    public EventCommand(String s) throws CommandException {
        super(s, pattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return String.join(
                "\n",
                "Invalid format for command `event`!",
                "Usage: event <DESCRIPTION> [/from <START_TIME> | /to <END_TIME>] [/to <END_TIME> | /from "
                        + "<START_TIME>]",
                "<START_TIME> and <END_TIME> should be of the format YYYY-MM-DDTHH:mm[:ss.sss]"
        );
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String description = matcher.group("description");
        String startFore = matcher.group("startFore");
        String endAft = matcher.group("endAft");
        String endFore = matcher.group("endFore");
        String startAft = matcher.group("startAft");

        if (description.isEmpty()) {
            throw new CommandException("Event description cannot be empty!");
        }

        boolean isFromTo = startFore != null;

        try {
            LocalDateTime startTime = LocalDateTime.parse(isFromTo ? startFore : startAft);
            LocalDateTime endTime = LocalDateTime.parse(isFromTo ? endAft : endFore);

            Event event = new Event(description, startTime, endTime);
            tasks.add(event);

            return new CommandResult(
                    true,
                    "Got it. I've added this task:",
                    event.toString(),
                    String.format(
                            "Now you have %d %s in the list.",
                            tasks.size(),
                            tasks.size() == 1 ? "task" : "tasks"
                    )
            );
        } catch (DateTimeParseException e) {
            throw new CommandException("Event start or end time is not a valid datetime!");
        }

    }
}
