package duke.core;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {
    private static boolean isExit = false;

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public static void main(String[] args) {
        try {
            Duke.tasks = new TaskList(Storage.readFile("tasks.txt"));
        } catch (DukeException e) {
            Ui.respond(e);
        }

        Ui.showGreetMessage();

        while (!isExit) {
            try {
                Command command = Ui.readCommand();

                if (command == null) {
                    continue;
                }

                command.execute(tasks, ui, storage);
                Duke.isExit = command.isExit();
            } catch (DukeException e) {
                Ui.respond(e);
            }
        }

        Ui.showExitMessage();
    }
}
