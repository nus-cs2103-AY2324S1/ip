package minion.commands;

import minion.data.task.Event;
import minion.storage.Storage;
import minion.data.TaskList;
import minion.ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(event);
        ui.print(
                "Got it. I've added this task:",
                "\t" + event.toString(),
                "Now you have " + tasks.size() +  " tasks in the list."
        );
        storage.writeToFile(tasks);
    }
}
