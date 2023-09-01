package duke.core;

import java.util.stream.Stream;

import duke.command.Command;

import duke.task.TaskList;

/**
 * Main class for the program.
 */
public class Duke {
    private static boolean isExit = false;

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public static void main(String[] args) {
        try {
            Stream<String> taskDataStream = Storage.readFile("tasks.txt");
            Duke.tasks = new TaskList(taskDataStream);

            if (tasks.hasLoadingError()) {
                Ui.showLoadingError();
            }

        } catch (DukeException e) {
            Ui.respond(e);
        }

        Ui.showGreetMessage();

        while (!isExit) {
            try {
                Command command = Ui.readCommand();

                if (command == null) {
                    Ui.showInputArrow();
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
