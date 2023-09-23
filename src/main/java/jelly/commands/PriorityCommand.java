package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

/**
 * Marks a task as low (3), medium(2), or high priority(1). Default priority of a task is high.
 */
public class PriorityCommand extends Command {
    private final int INDEX;
    private final int PRIORITY;

    /**
     * Constructor for a priority command.
     * @param index Index of the task to change priority.
     * @param priority Priority that is to be given to the task.
     */
    public PriorityCommand(int index, int priority) {
        INDEX = index;
        PRIORITY = priority;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert INDEX > 0 : "Index should be positive.";
        assert PRIORITY > 0 && PRIORITY <= 3 : "Priority should be between 1 and 3 inclusive.";
        if (INDEX <= 0 || INDEX > taskList.size()) {
            return ("Invalid input");
        }
        if (PRIORITY < 1 || PRIORITY > 3) {
            return ("Invalid input, please input 1, 2 or 3.");
        }
        taskList.get(INDEX - 1).changePriority(PRIORITY);
        storage.saveAndExit(taskList);
        return ("I've changed the priority of this task to: " + PRIORITY);
    }
}
