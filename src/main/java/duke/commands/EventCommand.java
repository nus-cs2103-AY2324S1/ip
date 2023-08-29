package duke.commands;

import duke.TaskList;
import duke.tasks.Event;

public class EventCommand extends Command {
    private final String args;

    public EventCommand(String args) {
        this.args = args;
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String[] tokens = args.split(" /from ", 2);
        String description = tokens[0];

        if (description.isEmpty()) {
            throw new CommandException("Event description cannot be empty!");
        }
        if (tokens.length != 2) {
            throw new CommandException("Event start and end dates cannot be empty!");
        }

        tokens = tokens[1].split(" /to ");
        String from = tokens[0];

        if (from.isEmpty()) {
            throw new CommandException("Event start date cannot be empty!");
        }
        if (tokens.length != 2) {
            throw new CommandException("Event end date cannot be empty!");
        }

        String to = tokens[1];
        Event event = new Event(description, from, to);
        tasks.add(event);

        return new CommandResult(true, "Got it. I've added this task:", event.toString(), String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }
}
