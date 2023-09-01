package duke;

import duke.commands.Command;
import duke.commands.CommandException;
import duke.commands.CommandResult;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.ui.TextUi;

public class Duke {
    private static final String NAME = "Jimmy";
    private static final String TASKS_CACHE_PATH = ".duke-cache";

    public static void main(String[] args) {
        TextUi ui = new TextUi();
        Storage storage = new Storage(TASKS_CACHE_PATH);
        TaskList tasks;

        try {
            tasks = storage.load();
            ui.say(String.format("Loaded existing tasks from %s", TASKS_CACHE_PATH));
        } catch (StorageException e) {
            ui.say(String.format("%s. Initializing empty task list...", e.getMessage()));
            tasks = new TaskList();
        }

        ui.greet(NAME);

        boolean shouldTerminate = false;

        while (!shouldTerminate) {
            String input = ui.getInput();

            try {
                Command command = Command.parse(input);

                if (command == null) {
                    continue;
                }
                if (command.shouldExit()) {
                    shouldTerminate = true;
                }

                CommandResult result = command.run(tasks);

                if (result.isTaskListDirty()) {
                    storage.save(tasks);
                }

                ui.say(result.getResponse().toArray(new String[0]));
            } catch (CommandException e) {
                ui.say(e.getMessage());
            }
        }

        storage.save(tasks);
    }
}
