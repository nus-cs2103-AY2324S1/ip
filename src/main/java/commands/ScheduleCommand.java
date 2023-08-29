package commands;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.DukeException;
import io.Storage;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class ScheduleCommand extends Command {
    protected static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private String output = "";

    public ScheduleCommand(String command) {
        super(command);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String input = getCommand();
        LocalDate queryDateTime;
        System.out.println(input.substring(9));
        try {
            queryDateTime = LocalDate.parse(input.substring(9), DATE_FORMAT_OUTPUT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy");
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof DeadlineTask) {
                DeadlineTask deadline = (DeadlineTask) task;
                if (deadline.getBy().toLocalDate().equals(queryDateTime)) {
                    output += (i + 1) + ". " + deadline.toString() + "\n";
                }
            } else if (task instanceof EventTask) {
                EventTask event = (EventTask) task;
                if (!event.getStartDate().toLocalDate().isAfter(queryDateTime)
                        & !event.getEndDate().toLocalDate().isBefore(queryDateTime)) {
                    output += (i + 1) + ". " + event.toString() + "\n";
                }
            }
        }
        ui.showSchedule(output);
    }
    public String getOutput() {
        return output;
    }
}
