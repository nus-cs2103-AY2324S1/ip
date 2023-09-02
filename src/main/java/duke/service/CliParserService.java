package duke.service;

import duke.Duke;
import duke.commands.Command;
import duke.exception.*;
import duke.tasks.Task;

import java.util.*;

public class CliParserService {
    private final Duke dukeBot;
    private final UiService uiService;
    private final CommandFactory commandFactory;

    public CliParserService(Duke dukeBot, UiService uiService, CommandFactory commandFactory) {
        this.dukeBot = dukeBot;
        this.uiService = uiService;
        this.commandFactory = commandFactory;
    }

    public void parse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine().trim();
            List<String> arguments = new ArrayList<>();

            // Split the command and its primary argument
            String[] splitBySpace = line.split(" ", 2);
            String commandType = splitBySpace[0];

            // If there's more than just the command, handle the arguments
            if (splitBySpace.length > 1) {
                // primaryArg refers to either task name, or the task index.
                String primaryArg = splitBySpace[1].split("/")[0].trim();
                arguments.add(primaryArg);

                // Split the rest by slashes
                String[] splitBySlash = splitBySpace[1].split("/");
                for (int i = 1; i < splitBySlash.length; ++i) { // Start at 1 to skip the task name
                    arguments.add(splitBySlash[i].trim());
                }
            }
            try {
                Command command = commandFactory.createCommand(commandType, arguments);
                if (command.isExit()) {
                    return;
                }
                command.execute();
            } catch (UnknownCommandException | InvalidCommandInputException e) {
                uiService.printGenericMessage(e.getMessage());
            }
        }
    }

    private void handleMarkTask(String[] input) {
        if (!isValidTaskNumberArgument(input)) {
            return;
        }
        int taskNumber = Integer.parseInt(input[1]);
        try {
            Optional<Task> optionalTask = dukeBot.markTask(taskNumber - 1);
            optionalTask.ifPresentOrElse(
                    uiService::printMarkTask,
                    () -> uiService.printInvalidTaskIndexProvided(taskNumber, dukeBot.getNumberOfTasks())
            );
        } catch (DukeStorageException e) {
            uiService.printStorageMarkFailure();
        }
    }

    private void handleUnmarkTask(String[] input) {
        if (!isValidTaskNumberArgument(input)) {
            return;
        }
        int taskNumber = Integer.parseInt(input[1]);
        try {
            Optional<Task> optionalTask = dukeBot.unmarkTask(taskNumber - 1);
            optionalTask.ifPresentOrElse(
                    uiService::printUnmarkTask,
                    () -> uiService.printInvalidTaskIndexProvided(taskNumber, dukeBot.getNumberOfTasks())
            );
        } catch (DukeStorageException e) {
            uiService.printStorageUnmarkFailure();
        }
    }

    private void handleDelete(String[] input) {
        if (!isValidTaskNumberArgument(input)) {
            return;
        }

        int taskNumber = Integer.parseInt(input[1]);
        try {
            Optional<Task> optionalTask = dukeBot.deleteTask(taskNumber - 1);

            optionalTask.ifPresentOrElse(
                    task -> uiService.printDeleteTask(task, dukeBot.getNumberOfTasks()),
                    () -> uiService.printInvalidTaskIndexProvided(taskNumber, dukeBot.getNumberOfTasks())
            );
        } catch (DukeStorageException e) {
            uiService.printStorageDeleteFailure();
        }
    }

    // Utility method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidTaskNumberArgument(String[] input) {
        if (input.length <= 1) {
            uiService.printGenericMessage("An argument is required.");
            return false;
        }
        if (!isNumeric(input[1])) {
            uiService.printGenericMessage("A numeric argument should be provided.");
            return false;
        }
        return true;
    }
}
