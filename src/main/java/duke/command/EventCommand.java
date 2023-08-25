package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.util.Objects;

public class EventCommand extends NonemptyArgumentCommand implements Command{

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
        String[] userArgs = arguments.split("/from |/to ");
        if (userArgs.length != 3) {
            throw new DukeException("Missing Argument for command: " +
                    commandString +
                    ", should include /from [date] /to [date]");
        }
        if (Objects.equals(userArgs[1], "") || Objects.equals(userArgs[2], "")) {
            throw new DukeException("Missing Argument for command: " +
                    commandString +
                    ", should include /from [date] /to [date]");
        }
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.arguments);
        String[] userArgs = arguments.split("/from |/to ");
        taskList.add(new Event(userArgs[0], userArgs[1], userArgs[2]));
        UI.sendMessage("Got it. I've added this task:\n  " +
                taskList.get(taskList.size()-1) +
                String.format("\nNow you have %d tasks in the list.", taskList.size()));
        storage.updateFile(taskList);
    }

    @Override
    public String toString() {
        return commandString;
    }
}
