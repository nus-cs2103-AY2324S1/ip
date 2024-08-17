package duke.command;

import duke.Storage;
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

    /**
     * Creates a ToDo in TaskList.
     *
     * @param list TaskList to be modified.
     * @return prompt for creating ToDo.
     */
    @Override
    public String execute(TaskList list) {
        list.add(task);
        Storage.instance.save(list);
        return Ui.instance.createTaskPrompt(task);
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
