package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnmatchedArgumentException;
import duke.task.Event;
import duke.task.TaskList;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class EventCommand extends Command {

    public EventCommand(String detail) {
        this.detail = detail;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws UnmatchedArgumentException, EmptyDescriptionException {

        String[] arr = this.detail.split(" /");
        Parser p = new Parser();
        if (arr.length < 3) {
            throw new UnmatchedArgumentException(arr.length, 3);
        }
        if (arr[0].isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        LocalDateTime start = p.checkDateAndTime(arr[1].substring(5).replace(" ", "/"));
        LocalDateTime end = p.checkDateAndTime(arr[2].substring(3).replace(" ", "/"));
        if (end.isBefore(start) || end.isEqual(start)) {
            throw new DateTimeException("The end is date should not be earlier or the same as the start date.");
        }
        Event e = new Event(arr[0], false, start, end);
        tasks.add(e);
        storage.writeInto(tasks);
        String result =  Ui.showLine() + "\n" + "Got it. I've added this task: " + "\n" + " " + e + "\n";
        result += "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }
}
