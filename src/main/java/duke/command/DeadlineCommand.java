package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.util.Objects;

public class DeadlineCommand extends NonemptyArgumentCommand implements Command{

    private static final String commandString = "deadline";
    private final String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
        String[] userArgs = arguments.split("/by ");
        if (userArgs.length != 2) {
            throw new DukeException("Missing Argument for command: " + commandString + ", should include /by [date]");
        }
        if (Objects.equals(userArgs[1], "")) {
            throw new DukeException("Missing Argument for command: " + commandString + ", should include /by [date]");
        }
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.arguments);
        String[] userArgs = arguments.split("/by ");
        taskList.add(new Deadline(userArgs[0], userArgs[1]));
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
