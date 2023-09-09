package ducky.command;

import ducky.Storage;
import ducky.TaskList;

/**
 * Represents a command that lists all tasks in Ducky's task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a command that lists all tasks in Ducky's task list.
     */
    public ListCommand() {}

    /**
     * Prints each task on each line as their printable form on the user interface.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return String containing all existing tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.getPrintableList();
    }
}
