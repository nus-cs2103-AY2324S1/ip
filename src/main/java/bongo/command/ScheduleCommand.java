package bongo.command;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.Task;
import bongo.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ScheduleCommand extends Command {
    private final LocalDate date;

    public ScheduleCommand(String[] command) throws BongoException {
        if (command.length <= 1) {
            throw new BongoException("Please include the date for which you wish to check the schedule.");
        }
        assert command.length == 2 : "Please use the command like this: schedule [date]";
        this.date = LocalDate.parse(command[1], DateHelper.dateFormatter);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BongoException {
        ArrayList<Task> scheduledTaskList = tasks.getAllTasks().stream()
                .filter(currentTask -> currentTask.isTaskScheduledForDate(this.date))
                .collect(Collectors.toCollection(ArrayList::new));

        return ui.showScheduledTasks(scheduledTaskList, this.date);
    }
}
