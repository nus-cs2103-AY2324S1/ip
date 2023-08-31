package duke;

import duke.exception.*;
import duke.taskClasses.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for interpreting user commands and executing the appropriate actions.
 */
public class Parser {

    /**
     * Enum representing the possible command types that can be executed.
     */
    private enum Command {
        TODO, DEADLINE, EVENT, BYE, MARK, UNMARK, LIST, DELETE, CLEAR, FIND
    }

    /**
     * Interprets and processes user input, then calls the appropriate action based on the input.
     *
     * @param ui       The UI instance used to handle outputs to the user.
     * @param storage  The Storage instance used for reading/writing data.
     * @param taskList The TaskList instance holding the user's tasks.
     */
    public static void run(Ui ui, Storage storage, TaskList taskList) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();
            ui.newDashedLine();
            String[] parts = input.split(" ");
            String[] details = input.split("/");
            Command command;

            try {
                command = getCommand(parts[0]);
                executeCommand(command, ui, storage, taskList, parts, details, input);

                if (command == Command.BYE) {
                    storage.writeToDB(taskList);
                    break;
                }

            } catch (DukeException e) {
                System.out.println(e);
            }

            ui.newDashedLine();
        }

        scanner.close();
    }

    /**
     * Retrieves the command based on the given input string.
     *
     * @param input The user's command string.
     * @return The corresponding Command.
     * @throws InvalidInputException If the input does not correspond to any known command.
     */
    private static Command getCommand(String input) throws InvalidInputException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Executes the appropriate actions based on the given command.
     *
     * @param command The command to be executed.
     * @param ui      The UI instance used for user interactions.
     * @param storage The storage instance for data persistence.
     * @param taskList The current list of tasks.
     * @param parts   The split input parts.
     * @param details The split details.
     * @param fullInput The complete user input string.
     * @throws DukeException If any error occurs during command execution.
     */
    private static void executeCommand(Command command, Ui ui, Storage storage, TaskList taskList,
                                       String[] parts, String[] details, String fullInput) throws DukeException {

        // retrieve the task number for commands that require it
        Integer number = (command == Command.MARK || command == Command.UNMARK || command == Command.DELETE)
                ? getTaskNumber(parts, taskList) : null;

        switch (command) {
            case BYE:
                ui.printBye();
                break;

            case DELETE:
                String content = taskList.deleteTask(number);
                ui.deleteTask(content);
                ui.printTaskCount(taskList.getTasksCount());
                break;

            case LIST:
                ui.printList(taskList);
                break;

            case UNMARK:
                taskList.markTaskAsNotDone(number);
                ui.printTaskMarkAsNotDone(taskList.getStatusAndDescription(number));
                break;

            case MARK:
                taskList.markTaskAsDone(number);
                ui.printTaskMarkAsDone(taskList.getStatusAndDescription(number));
                break;

            case TODO:
                String toDoDescription = details[0].trim();
                if (toDoDescription.length() <= 4) {
                    throw new InvalidToDoException();
                }
                taskList.addToDoToList(false, toDoDescription.substring(5).trim());
                break;

            case DEADLINE:
                if (details[0].trim().length() <= 8 || !fullInput.contains("by")) {
                    throw new InvalidDeadlineException();
                }
                String deadlineDescription = details[0].split("/")[0].substring(9).trim();
                String byTime = fullInput.split("by")[1].trim();
                taskList.addDeadlineToList(false, deadlineDescription, byTime);
                break;

            case EVENT:
                if (details[0].trim().length() <= 5 || !fullInput.contains("from") || !fullInput.contains("to")) {
                    throw new InvalidEventException();
                }
                String eventDescription = details[0].split("/")[0].substring(6).trim();
                String fromTime = fullInput.split("from")[1].split("/to")[0].trim();
                String toTime = fullInput.split("to")[1].trim();
                taskList.addEventToList(false, eventDescription, fromTime, toTime);
                break;

            case CLEAR:
                taskList.clear();
                break;

            case FIND:
                String findDescription = details[0].trim();
                if (findDescription.length() <= 4) {
                    throw new InvalidFindException();
                }
                ui.printTaskContainingKeyword(taskList, findDescription.substring(5).trim());
                break;
            default:
                throw new InvalidInputException();
        }
    }


    /**
     * Retrieves the task number from the input parts and checks if it's valid.
     *
     * @param parts Parts of the command input.
     * @param taskList The current list of tasks.
     * @return The task number.
     * @throws InvalidListNumberException If the task number is invalid.
     */
    private static int getTaskNumber(String[] parts, TaskList taskList) throws InvalidListNumberException {
        try {
            int number = Integer.parseInt(parts[1]);
            if (number < 0 || number > taskList.getTasksCount()) {
                throw new InvalidListNumberException();
            }
            return number;
        } catch (Exception e) {
            throw new InvalidListNumberException();
        }
    }
}

