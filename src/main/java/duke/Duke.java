package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.storage.Storage;

import java.util.Scanner;

public class Duke {
    private static final int INDENT_SIZE = 4;
    private static final String NAME = "Jimmy";
    private static final String TASKS_CACHE_PATH = ".duke-cache";
    public static TaskList tasks;

    private static String indent(String s) {
        return String.format("%s%s", " ".repeat(INDENT_SIZE), s);
    }

    private static void whisper(String... strings) {
        for (String s : strings) {
            System.out.println(indent(s));
        }
    }

    private static void say(String... strings) {
        whisper(strings);
        System.out.println();
    }

    private static void greet() {
        say(String.format("Hello! I'm %s", NAME), "What can I do for you?");
    }

    public static void main(String[] args) {
        Storage storage = new Storage(TASKS_CACHE_PATH);
        try {
            tasks = storage.load();
            say(String.format("Loaded existing tasks from %s", TASKS_CACHE_PATH));
        } catch (DukeException e) {
            say(String.format("%s. Initializing empty task list...", e.getMessage()));
            tasks = new TaskList();
        }

        greet();

        boolean shouldTerminate = false;
        Scanner sc = new Scanner(System.in);

        while (!shouldTerminate && sc.hasNextLine()) {
            String input = sc.nextLine().trim();

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

                say(result.response.toArray(new String[0]));
            } catch (DukeException e) {
                say(e.getMessage());
            }
        }

        storage.save(tasks);
    }
}
