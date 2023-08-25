package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class EventCommand extends NonemptyArgumentCommand implements Command {

    private static final String commandString = "event";
    private final String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
        String[] userArgs = arguments.split("/from ");
        if (userArgs.length != 2) {
            throw new DukeException("Missing Argument for command: " +
                    commandString +
                    ", should include /from [date] /to [date]");
        }
        String desc = userArgs[0];
        String[] subcommandArgs = userArgs[1].split("/to ");
        if (subcommandArgs.length != 2) {
            throw new DukeException("Missing Argument for command: " +
                    commandString +
                    ", should include /from [date] /to [date]");
        }
        if (Objects.equals(subcommandArgs[0], "") || Objects.equals(subcommandArgs[1], "")) {
            throw new DukeException("Missing Argument for command: " +
                    commandString +
                    ", should include /from [date] /to [date]");
        }
        try {
            LocalDate date = LocalDate.parse(subcommandArgs[0].trim());
            date = LocalDate.parse(subcommandArgs[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date Format for command: " +
                    commandString +
                    "should be /from YYYY-MM-DD /to YYYY-MM-DD");
        }
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.arguments);
        String[] userArgs = arguments.split("/from |/to ");
        LocalDate from = LocalDate.parse(userArgs[1].trim());
        LocalDate to = LocalDate.parse(userArgs[2].trim());
        taskList.add(new Event(userArgs[0].trim(), from, to));
        UI.sendMessage("Got it. I've added this task:\n  " +
                taskList.get(taskList.size() - 1) +
                String.format("\nNow you have %d tasks in the list.", taskList.size()));
        storage.updateFile(taskList);
    }

    @Override
    public String toString() {
        return commandString;
    }
}
