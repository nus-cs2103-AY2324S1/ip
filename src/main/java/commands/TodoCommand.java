package commands;

import data.Actions;
import duke.DukeException;
import tasks.Todo;
import ui.UI;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(" Provide the description. "
                    + "Format: todo task");
        }
        Todo task = new Todo(description);
        actionList.add(task);
        ui.lineSandwich(" Got it. I've added this task:\n  " + task + "\n Now you have "
                + actionList.size() + " tasks in the list.");
    }
}
