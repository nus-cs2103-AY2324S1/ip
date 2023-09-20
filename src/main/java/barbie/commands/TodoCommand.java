package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Task;
import barbie.types.Todo;

/**
 * Represents a command when a "todo" is called by the user.
 */
public class TodoCommand extends Command {
    private String desc;

    /**
     * Constructs an instance of the TodoCommand and saves the description.
     * @param desc description of the task
     */
    public TodoCommand(String desc) {
        this.desc = desc;
        this.isExit = false;

    }


    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application
     * @param taskList current list of tasks
     * @return String to be returned to user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        Todo todo = new Todo(this.desc);
        taskList.add(todo);
        Storage.addToList(this.desc);
        return Ui.taskAdded(todo);

    }
}
