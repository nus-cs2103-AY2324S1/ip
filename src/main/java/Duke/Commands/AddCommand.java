package Duke.Commands;

import Duke.Exceptions.BackwardsTimeException;
import Duke.Exceptions.NoDescriptionException;
import Duke.Exceptions.UnknownCommandException;
import Duke.Exceptions.UnknownTimeException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;
import Duke.Tools.Parser;
import Duke.Tools.Storage;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {

    private final DateTimeFormatter formatter;

    public AddCommand(String fullCommand, DateTimeFormatter formatter) {
        super(fullCommand);
        this.formatter = formatter;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws NoDescriptionException, UnknownTimeException, BackwardsTimeException, UnknownCommandException {
        Parser.Operations operation = Parser.Operations.valueOf(
                this.fullCommand.replaceAll("^\\W*\\b(\\w+).*", "$1").toUpperCase());
        Task task;

        switch (operation) {
        case TODO:
            String desc = this.fullCommand.replaceAll("^\\s*todo\\s*", "");
            if (desc.equals("")) {
                throw new NoDescriptionException("todo");
            }

            task = new ToDo(desc);
            tasks.add(task);
            ui.showAddMessage(task, tasks.size());
            break;
        case DEADLINE:
            String deadlineTime = this.fullCommand.replaceAll("^\\s*deadline\\s*", "");

            String[] strings = deadlineTime.split(" /by ");

            if (deadlineTime.equals("")) {
                throw new NoDescriptionException("deadline");
            }
            if (strings.length == 1) {
                throw new UnknownTimeException(strings[0]);
            }

            task = new Deadline(strings[0],
                    LocalDateTime.parse(strings[1], this.formatter));
            tasks.add(task);
            ui.showAddMessage(task, tasks.size());
            break;
        case EVENT:
            String content = this.fullCommand.replaceAll("^\\s*event\\s*", "");
            if (content.equals("")) {
                throw new NoDescriptionException("event");
            }

            String[] descTime = content.split(" /from ");
            String[] times = descTime[1].split(" /to ");

            if (times.length == 1) {
                throw new UnknownTimeException(descTime[0]);
            }

            LocalDateTime start = LocalDateTime.parse(times[0], this.formatter);
            LocalDateTime end = LocalDateTime.parse(times[1], this.formatter);

            if (start.isAfter(end)) {
                throw new BackwardsTimeException();
            }

            task = new Event(descTime[0], start, end);
            tasks.add(task);
            ui.showAddMessage(task, tasks.size());
            break;
        default:
            throw new UnknownCommandException(this.fullCommand);
        }

    }
}
