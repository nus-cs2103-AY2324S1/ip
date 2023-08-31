package duke.command;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that adds a task to the existing task list.
 */
public class AddCommand extends Command {
    protected Task taskToBeAdded ;

    public AddCommand(Task taskToBeAdded){
        this.taskToBeAdded = taskToBeAdded ;
    }

    /**
     * Modifies the existing list by adding the task provided by the user.
     * @param taskList the existing task list of the user
     * @param ui the ui that handles successful/unsuccessful messages
     */
    public void execute(TaskList taskList, Ui ui){
        taskList.addTask(this.taskToBeAdded);
        ui.showAddedMessage();
        System.out.println(this.taskToBeAdded.toString());
        ui.showTaskListSize(taskList);
    }

}
