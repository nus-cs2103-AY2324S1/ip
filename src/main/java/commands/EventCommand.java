package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;
import duke.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command{
    private Task eventToAdd;

    private LocalDateTime from;
    private LocalTime to;

    private String fromInString;
    private String toInString;

    private final DateTimeFormatter FROM_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
    private final DateTimeFormatter TO_FORMATTER = DateTimeFormatter.ofPattern("HHmm");


    public EventCommand(String[] taskInfo) {
        String taskDescription = taskInfo[0];
        this.fromInString = taskInfo[1].substring("from ".length(), "from ".length() + "yyyyMMdd HHmm".length());
        this.toInString = taskInfo[2].substring("to ".length());

        this.from = LocalDateTime.parse(fromInString, FROM_FORMATTER);
        this.to = LocalTime.parse(toInString, TO_FORMATTER);

        this.eventToAdd = new Event(taskDescription, from, to, false);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(this.eventToAdd);
        storage.appendToFile("E | " + this.eventToAdd.getStatusIcon() + " | "
                + this.eventToAdd.getTaskDescription() + " | " + this.fromInString + "-" + this.toInString
                + System.lineSeparator());
        return ui.printAddedTask(tasks.getSize(), this.eventToAdd);
    }
}
