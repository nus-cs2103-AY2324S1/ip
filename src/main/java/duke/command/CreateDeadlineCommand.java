package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDate;

public class CreateDeadlineCommand extends Command {

    private Deadline task;

    public CreateDeadlineCommand(String task, LocalDate by) {
        this.task = new Deadline(task,by);
    }
    @Override
    public void execute(TaskList list) {
        list.add(task);
        Ui.ui.createTaskPrompt(task);
        Duke.run();
    }

    public Deadline task(){
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CreateDeadlineCommand) {
            CreateDeadlineCommand temp = (CreateDeadlineCommand) o;
            return this.task.equals(temp.task());
        }
        return false;
    }

}
