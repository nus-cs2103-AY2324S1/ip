package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnmatchedArgumentException;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String detail) {
        super(detail);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws UnmatchedArgumentException, EmptyDescriptionException {

        String[] arr = this.detail.split(" /");
        Parser p = new Parser();
        if (arr.length < 2) {
            throw new UnmatchedArgumentException(arr.length, 2);
        }
        if (arr[0].isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        String dateAndTime = arr[1].substring(3).replace(" ", "/");
        Deadline dl = new Deadline(arr[0], false, p.checkDateAndTime(dateAndTime));
        tasks.add(dl);
        storage.writeInto(tasks);
        String result = Ui.showLine() + "\n" + "Got it. I've added this task: " + "\n" + " " + dl + "\n";
        result += "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }
}

