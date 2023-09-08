package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Command to create ToDo task
 *
 * @author Lian Zhi Xuan
 */
public class CreateToDoCommand extends Command{

    private ToDo task;

    public CreateToDoCommand(String task) {
        this.task = new ToDo(task);
    }
    @Override
    public String execute(TaskList list) {
        list.add(task);
        return Ui.ui.createTaskPrompt(task);
    }

    public ToDo task(){
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CreateToDoCommand) {
            CreateToDoCommand temp = (CreateToDoCommand) o;
            return this.task.equals(temp.task());
        }
        return false;
    }


}
