package duke.command;

import duke.Ui;
import duke.task.Events;
import duke.task.TaskList;

import java.time.LocalDate;

/**
 * Command to create event task
 *
 * @author Lian Zhi Xuan
 */
public class CreateEventCommand extends Command {

    private Events task;

    public CreateEventCommand(String task, LocalDate from, LocalDate to) {
        this.task = new Events(task, from, to);
    }

    @Override
    public String execute(TaskList list) {
        list.add(task);
        return Ui.instance.createTaskPrompt(task);
    }

    public Events task(){
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CreateEventCommand) {
            CreateEventCommand temp = (CreateEventCommand) o;
            return this.task.equals(temp.task());
        }
        return false;
    }

}