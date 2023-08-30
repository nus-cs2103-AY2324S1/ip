package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.Event;
import cyrus.tasks.Task;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;
import cyrus.utility.DateUtility;

import java.time.LocalDate;
import java.util.HashMap;

public class AddEventCommand extends Command {
    public AddEventCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    @Override
    public void execute() throws CommandError {
        if (this.parseInfo.hasNoArgument()) {
            throw new CommandError("Event is missing a body!");
        }

        HashMap<String, String> options = this.parseInfo.getOptions();
        if (!options.containsKey("from")) {
            throw new CommandError("Invalid event format: missing /from");
        }
        if (!options.containsKey("to")) {
            throw new CommandError("Invalid event format: missing /to");
        }

        String eventName = this.parseInfo.getArgument();
        String eventFrom = options.get("from");
        String eventTo = options.get("to");

        LocalDate eventFromDate = DateUtility.parse(eventFrom);
        LocalDate eventToDate = DateUtility.parse(eventTo);

        if (eventFrom == null) {
            throw new CommandError("Invalid event format: invalid from string, must be format" +
                    " dd/MM/yyyy");
        }

        if (eventToDate == null) {
            throw new CommandError("Invalid event format: invalid to string, must be format" +
                    " dd/MM/yyyy");
        }

        Task event = new Event(eventName, eventFromDate, eventToDate);
        this.taskList.addTask(event);
        Ui.printAddTask(event, this.taskList.size());
    }
}
