package command;

import main.Storage;
import main.UI;
import task.Task;
import task.TaskList;

/**
 * FindCommand is a subclass of Command.
 * Used to execute Find Commands.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String fullCommand) {
        this.keyword = fullCommand.substring(5);
    }

    /**
     *
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @return find message to be printed in the GUI
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        TaskList findList = new TaskList();

        for (Task t : taskList.getTaskArrayList()) {
            if(t.getDescription().contains(this.keyword)) {
                findList.addTask(t);
            }
        }

        String message = ui.findList(findList.getTaskArrayList(), true);
        return message;
    }
}
