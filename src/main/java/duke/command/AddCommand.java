package duke.command;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    protected Task taskToBeAdded ;

    public AddCommand(Task taskToBeAdded){
        this.taskToBeAdded = taskToBeAdded ;
    }
    public void execute(TaskList taskList, Ui ui){
        taskList.addTask(this.taskToBeAdded);
        ui.showAddedMessage();
        System.out.println(this.taskToBeAdded.toString());
        ui.showTaskListSize(taskList);
    }

}
