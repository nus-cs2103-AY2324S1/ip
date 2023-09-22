package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Formatter;

import java.util.Scanner;

public class EditCommand extends Command {
    private String command;

    private Scanner tokeniser;

    private Task taskToEdit;

    public EditCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    public EditCommand() {

    }

    /**
     * Gets task to edit.
     *
     * @return Task to edit.
     */
    public Task getEditTask() {
        return taskToEdit;
    }

    /**
     * Acts on tasks with command.
     *
     * @param tasks task list.
     * @return Response upon acting on task with command.
     * @throws IllegalCommandException invalid command formats.
     */
    public Task actOn(TaskList tasks) {
        // nothing specified after command
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        // specified content is not an integer
        String content = tokeniser.next();
        if (!isInteger(content)) {
            throw new IllegalCommandException("do that... try a number instead");
        }
        // if number of task does not exist
        int id = Integer.parseInt(content);
        if (id > tasks.getNumberOfTask() || id <= 0) {
            throw new IllegalCommandException("do that... this task does not exist :(");
        }
        return tasks.get(id - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return "Seems like you can't do that edit!\n"
                + "You are currently modifying this task: \n"
                + Formatter.indentLineBy(oldTask.toString(), 2)
                + "Type \"C\" to cancel";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws IllegalCommandException {
        this.taskToEdit = actOn(tasks);
        return "You are currently modifying this task: \n"
                + Formatter.indentLineBy(taskToEdit.toString(), 2)
                + "Type \"C\" to cancel";
    }
}
