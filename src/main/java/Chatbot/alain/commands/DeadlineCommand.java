package chatbot.alain.commands;

import chatbot.alain.AlainException;
import chatbot.alain.Assertions;
import chatbot.alain.TaskList;
import chatbot.alain.tasks.Deadline;
import chatbot.alain.uis.GuiUi;
import chatbot.alain.uis.Ui;

/**
 * Represents an abstract command that can be executed. This serves as the base class
 * for various specific command implementations in the system.
 *
 * <p>Each command is associated with a {@code TaskList} to keep track of tasks,
 * a textual representation or description of the command itself, and a {@code Storage}
 * instance for persistence concerns.</p>
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(TaskList list, String text) {
        super(list, text);
    }

    @Override
    public String processCommand() throws AlainException {
        if (text.length() <= 8) {
            throw new AlainException("The description of a Deadline cannot be empty.");
        }
        String mission = text.substring(9);
        if (mission.length() == 0) {
            throw new AlainException("The description of a Deadline cannot be empty.");
        }
        String[] parts = mission.split("/by ");
        if (parts.length != 2) {
            throw new AlainException("The description of a Deadline is invalid.");
        }
        Deadline newDeadline = new Deadline(parts[0], parts[1]);
        list.addTask(newDeadline);
        Assertions.assertNewDeadline(list, newDeadline);
        Ui.showAddTask(list.getTask(list.size() - 1), list);
        return GuiUi.showAddTask(list.getTask(list.size() - 1), list);
    }
}
