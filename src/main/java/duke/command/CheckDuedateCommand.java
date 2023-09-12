package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckDuedateCommand extends Command {

    public CheckDuedateCommand(String detail) {
        super(detail);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> dueDateList = new ArrayList<>();
        Parser p = new Parser();
        LocalDateTime date = p.checkDateAndTime(this.detail.replace(" ", "/"));

        for (Task t : tasks.getTasks()) {
            if (t instanceof Deadline &&
                    ((Deadline) t).getDueDate().isEqual(date)){
                dueDateList.add(t);
            } else if (t instanceof Event &&
                    (((Event) t).getStart().isEqual(date) ||
                            ((Event) t).getEnd().isEqual(date))) {
                dueDateList.add(t);
            }
        }
        boolean isGreaterThan1 = dueDateList.size() > 1;
        String result = "\tHere " + (isGreaterThan1 ? "are" : "is") +" the " +
                (isGreaterThan1 ? "tasks that contain" : "task that contains") + " the date:\n";
        for (Task t : dueDateList) {
            result += t + "\n";
        }
        result += Ui.showLine();
        result += "\n";
        return result;
    }
}
