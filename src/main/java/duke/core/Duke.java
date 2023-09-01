package duke.core;

import java.util.Scanner;

import duke.command.GreetCommand;
import duke.task.TaskList;

/**
 * Main class for the program.
 */
public class Duke {
    private static Boolean programRunning = true;

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Stops the program.
     */
    public static void stop() {
        Duke.programRunning = false;
    }

    public static void main(String[] args) {
        try {
            Duke.tasks = new TaskList(Storage.readFile("tasks.txt"));
        } catch (DukeException e) {
            Ui.respond(e);
        }

        new GreetCommand().execute(tasks, ui, storage);

        Scanner scanner = new Scanner(System.in);

        while (programRunning) {
            String input = scanner.nextLine();
            System.out.println();

            if (input.trim().equals("")) {
                continue;
            }
            try {
                Parser.parseCommand(input).execute(tasks, ui, storage);
            } catch (DukeException e) {
                Ui.respond(e);
            }
        }

        scanner.close();
    }
}
