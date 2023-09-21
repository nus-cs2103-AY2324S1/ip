package commands;

import ari.Storage;
import ari.TaskList;
import ari.Ui;
import ari.Task;
import ari.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DeadlineCommand class for the command that asks to create a new deadline
 */
public class DeadlineCommand extends Command{

    private Task deadlineToAdd;
    private LocalDateTime by;

    private String byInString;
    private final DateTimeFormatter DEADLINE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");


    public DeadlineCommand(String[] taskInfo) {
        String taskDescription = taskInfo[0];
        this.byInString = taskInfo[1].substring("by ".length());
        this.by = LocalDateTime.parse(byInString, DEADLINE_FORMATTER);
        this.deadlineToAdd = new Deadline(taskDescription, by, false);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(this.deadlineToAdd);
        storage.appendToFile("D | " + this.deadlineToAdd.getStatusIcon() + " | "
                + this.deadlineToAdd.getTaskDescription() + " | " + this.byInString
                + System.lineSeparator());
        return ui.printAddedTask(tasks.getSize(), this.deadlineToAdd);
    }
}
