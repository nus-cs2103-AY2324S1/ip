package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDate;

/**
 * Command to create a deadline task
 *
 * @author Lian Zhi Xuan
 */
public class CreateDeadlineCommand extends Command {

    private Deadline task;

    public CreateDeadlineCommand(String task, LocalDate by) {
        this.task = new Deadline(task,by);
    }
    @Override
    public String execute(TaskList list) {
        list.add(task);
        return Ui.ui.createTaskPrompt(task);
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
