package max.commands;

import max.storage.Storage;
import max.tasks.Task;
import max.tasks.TaskList;
import max.ui.Ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String item;

    /**
     * Specifies item to be found.
     *
     * @param item Item to be found
     */
    public FindCommand(String item) {
        this.item = item;
    }

    /**
     * Executes find command. Searches current list of tasks for matching string, and prints matching tasks.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> currList =  tasks.getList();
        ArrayList<Task> filterList = new ArrayList<>();
        for (Task task : currList) {
            if (task.getItem().contains(item)) {
                filterList.add(task);
            }
        }
        ui.showList(filterList);
    }

    /**
     * Checks if given command is exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}