package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class CheckCommand extends Command {

    private final DateTimeFormatter formatter;

    public CheckCommand(String fullCommand, DateTimeFormatter formatter) {
        super(fullCommand);
        this.formatter = formatter;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String content = fullCommand.replaceAll("^\\s*check\\s*", "");
        ui.printScheduledTasks(tasks, LocalDateTime.parse(content, formatter));
    }
}
