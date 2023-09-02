package duke.service;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.exception.TaskParseException;
import duke.exception.TimeUtilException;
import duke.tasks.Task;

import java.util.*;

public class CliParserService {
    private final Duke dukeBot;
    private final UiService uiService;
    private final TaskFactory taskFactory;

    public CliParserService(Duke dukeBot, UiService uiService, TaskFactory taskFactory) {
        this.dukeBot = dukeBot;
        this.uiService = uiService;
        this.taskFactory = taskFactory;
    }

    public void parse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String[] input = line.split(" ");
            String command = input[0].toLowerCase();

            switch (command) {
                case "bye":
                    return;
                case "list":
                    uiService.printTaskList(dukeBot.getTaskList());
                    break;
                case "mark":
                    handleMarkTask(input);
                    break;
                case "unmark":
                    handleUnmarkTask(input);
                    break;
                case "delete":
                    handleDelete(input);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    parseTaskCommand(line);
                    break;
                default:
                    uiService.printUnknownCommand(input[0]);
            }
        }
    }

    private void parseTaskCommand(String line) {
        String[] parsedInput = line.split("/");
        String[] temp = parsedInput[0].split(" ", 2);
        String taskType = temp[0];
        String taskName = temp.length > 1 ? temp[1] : "";

        String[] taskArgs = new String[parsedInput.length - 1];
        for (int i = 1; i < parsedInput.length; i++) {
            taskArgs[i-1] = parsedInput[i].trim();
        }

        try {
            Task task = taskFactory.createTask(taskType, taskName, taskArgs);
            dukeBot.addTask(task);
            uiService.printAddTask(task, dukeBot.getNumberOfTasks());
        } catch (TaskParseException | TimeUtilException e) {
            uiService.printGenericMessage(e.getMessage());
        } catch (DukeStorageException e) {
            uiService.printStorageAddFailure();
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
