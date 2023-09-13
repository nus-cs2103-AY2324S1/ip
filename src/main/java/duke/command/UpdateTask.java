package duke.command;

import duke.Task;
import duke.TaskList;

/** Abstraction to update the content of a task. */
public class UpdateTask extends Command {

    private TaskList list;
    private String specifications;

    /**
     * Creates a commant that will update a task.
     *
     * @param list List containing the task to be updated.
     * @param specifications Description of how to update the task.
     */
    public UpdateTask(TaskList list, String specifications) {
        this.list = list;
        this.specifications = specifications;
    }

    @Override
    public String execute() {
        try {
            String[] splits = this.specifications.split(" ", 2);
            String taskIndex = splits[0];
            Integer index = Integer.parseInt(taskIndex);
            Task task = this.list.retrieve(index - 1);
            return task.update(splits[1]);
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! Please ensure that task is in list "
                    + "or add the description to be updated");
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("OOPS!!! Update has to be in format "
                    + "update <index> <description>.");
        }
    }
}
