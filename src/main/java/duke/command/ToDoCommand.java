package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class ToDoCommand extends Command {

    private String description;

    public ToDoCommand(String description) {
        super(CommandType.TODO);
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui) {
        Task task = new ToDo(description);
        tasks.addTask(task);
        ui.showTodoMessage(task);
        ui.showTaskListSizeMessage(tasks.getSize(), true);
    }
}
