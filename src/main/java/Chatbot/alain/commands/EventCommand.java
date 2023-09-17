package chatbot.alain.commands;

import chatbot.alain.AlainException;
import chatbot.alain.Assertions;
import chatbot.alain.uis.GuiUi;
import chatbot.alain.Storage;
import chatbot.alain.TaskList;
import chatbot.alain.uis.Ui;
import chatbot.alain.tasks.Event;

/**
 * Represents an abstract command that can be executed. This serves as the base class
 * for various specific command implementations in the system.
 *
 * <p>Each command is associated with a {@code TaskList} to keep track of tasks,
 * a textual representation or description of the command itself, and a {@code Storage}
 * instance for persistence concerns.</p>
 */
public class EventCommand extends Command {
    public EventCommand(TaskList list, String text, Storage storage) {
        super(list, text, storage);
    }

    @Override
    public String processCommand() throws AlainException {
        if (text.length() <= 5) {
            throw new AlainException("The description of a Event cannot be empty.");
        }
        String mission = text.substring(6);
        if (mission.length() == 0) {
            throw new AlainException("The description of a Event cannot be empty.");
        }
        String[] parts = mission.split("/");
        if (parts.length != 3) {
            throw new AlainException("The description of a Event is invalid.");
        }
        Event newEvent = new Event(parts[0],
                parts[1].substring(5), parts[2].substring(3));
        list.addTask(newEvent);
        Assertions.assertNewEvent(list, newEvent);
        Ui.showAddTask(list.getTask(list.size() - 1), list);
        return GuiUi.showAddTask(list.getTask(list.size() - 1), list);
    }
}
