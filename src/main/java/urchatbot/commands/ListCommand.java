package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

/**
 * Lists all tasks in the tasklist to the user.
 */
public class ListCommand extends Command {
    /**
     * Constructs the ListCommand class.
     *
     * @param taskDescription Task description of the task.
     */
    public ListCommand(String taskDescription) {
        super(taskDescription);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListMessage();
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println(i + 1 + "." + tasks.getTasks().get(i).toString());
        }
    }

}
