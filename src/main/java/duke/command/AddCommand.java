package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/** Command to add task into TaskList **/
public class AddCommand implements Command {
    private final Task task;

    /**
     * Initializes Add Command.
     *
     * @param task Task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to TaskList and saves list into file.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        storage.save(taskList.getTasks());
        setOutput("Task " + task.getTask() + " successfully added \n" + taskList.printSize());
    }

    /**
     * Replaces existing string in Output with a new string.
     *
     * @param string String to be replaced.
     */
    private void setOutput(String string) {
        OUTPUT.delete(0, OUTPUT.length());
        OUTPUT.append(string);
    }
}
