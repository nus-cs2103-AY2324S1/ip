package chatbot.alain.commands;

import chatbot.alain.AlainException;
import chatbot.alain.Assertions;
import chatbot.alain.TaskList;
import chatbot.alain.tasks.ToDo;
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
public class TodoCommand extends Command {
    public TodoCommand(TaskList list, String text) {
        super(list, text);
    }

    @Override
    public String processCommand() throws AlainException {
        if (text.length() <= 4) {
            throw new AlainException("The description of a Todo cannot be empty.");
        }
        String mission = text.substring(5);
        if (mission.length() == 0) {
            throw new AlainException("The description of a Todo cannot be empty.");
        }
        ToDo newTodo = new ToDo(mission);
        list.addTask(newTodo);
        Assertions.assertNewTodo(list, newTodo);
        Ui.showAddTask(list.getTask(list.size() - 1), list);
        return GuiUi.showAddTask(list.getTask(list.size() - 1), list);
    }
}
