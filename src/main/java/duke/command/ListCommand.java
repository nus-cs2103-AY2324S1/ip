package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public class ListCommand implements Command {

    private static final String commandString = "list";
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        if (output.length() == 0) {
            UI.sendMessage("No Items in List");
        } else {
            UI.sendMessage(output.toString());
        }
    }

    @Override
    public String toString() {
        return commandString;
    }
}
