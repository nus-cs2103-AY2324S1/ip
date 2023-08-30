package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.storage.Storage;
import duke.ui.TextUi;

public class Duke {
    private static final String NAME = "Jimmy";
    private static final String TASKS_CACHE_PATH = ".duke-cache";
    public static TaskList tasks;

    public static void main(String[] args) {
        TextUi ui = new TextUi();
        Storage storage = new Storage(TASKS_CACHE_PATH);
        try {
            tasks = storage.load();
            ui.say(String.format("Loaded existing tasks from %s", TASKS_CACHE_PATH));
        } catch (DukeException e) {
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
                if (command.isExit()) {
                    shouldTerminate = true;
                }

                CommandResult result = command.run(tasks);

                if (result.shouldSave) {
                    storage.save(tasks);
                }

                ui.say(result.response.toArray(new String[0]));
            } catch (DukeException e) {
                ui.say(e.getMessage());
            }
        }

        storage.save(tasks);
    }
}
