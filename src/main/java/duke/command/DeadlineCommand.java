package duke.command;

import duke.Ui;
import duke.command.Command;
import duke.Storage;
import duke.task.*;

public class DeadlineCommand extends Command {
    protected String description;
    protected String by;
    protected boolean done;
    public DeadlineCommand(String description, String by, boolean done) {
        super();
        this.description = description;
        this.by = by;
        this.done = done;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = new Deadlines(description, by);
            task.setDone(this.done);
            taskList.addTask(task);
            System.out.println(taskList);
        } catch (Exception e) {
            System.out.println(ui.format_response(e.getLocalizedMessage()));
        }
    }
}
