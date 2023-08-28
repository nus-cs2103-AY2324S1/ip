package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.TaskList;
import duke.task.ToDo;

public class CreateToDoCommand extends Command{

    private ToDo task;

    public CreateToDoCommand(String task) {
        this.task = new ToDo(task);
    }
    @Override
    public void execute(TaskList list) {
        list.add(task);
        Ui.ui.createTaskPrompt(task);
        Duke.run();
    }

}
