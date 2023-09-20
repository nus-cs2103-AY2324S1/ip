package commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * PostponeCommand class for command that asks to postpone a deadline
 */
public class PostponeCommand extends Command {

    private int indexToPostpone;

    private String newDeadlineInStr;
    private LocalDateTime newDeadline;
    private final DateTimeFormatter DEADLINE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");

    public PostponeCommand(String[] taskInfo) {
        this.indexToPostpone = java.lang.Integer.parseInt(taskInfo[0].substring(0, 1)) - 1;
        this.newDeadlineInStr = taskInfo[1].substring("by ".length());
        this.newDeadline = LocalDateTime.parse(newDeadlineInStr, DEADLINE_FORMATTER);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        tasks.postponeDeadline(this.indexToPostpone, this.newDeadline);
        storage.updateFileAfterPostpone(tasks.getTasks().get(this.indexToPostpone),
                this.indexToPostpone + 1,
                newDeadlineInStr);
        return ui.printAfterPostpone(tasks.getTasks().get(this.indexToPostpone));
    }
}
