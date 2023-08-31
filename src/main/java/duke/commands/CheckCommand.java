package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckCommand extends Command {

    private final DateTimeFormatter formatter;

    public CheckCommand(String fullCommand, DateTimeFormatter formatter) {
        super(fullCommand);
        this.formatter = formatter;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String content = this.fullCommand.replaceAll("^\\s*check\\s*", "");
        ui.printScheduledTasks(tasks, LocalDateTime.parse(content, this.formatter));
    }
}
