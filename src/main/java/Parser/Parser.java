package parser;

import java.io.FileNotFoundException;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.DeadLineCommand;
import command.DeleteCommand;
import command.EchoCommand;
import command.EventCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.StatisticsCommand;
import command.ToDoCommand;
import command.UnmarkCommand;
import duke.DukeException;
import task.Add;
import task.DeadLine;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * The `Parser` class is responsible for parsing user input and converting
 * it into appropriate commands and tasks for the BloopBot application.
 * It provides methods to parse user commands and task data from input strings.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Parser {
    /**
     * Parses a string containing task data and creates the corresponding `Task` object.
     *
     * @param taskData The string containing task data.
     * @return The `Task` object created from the task data.
     * @throws FileNotFoundException If the file for task data is not found.
     * @throws DukeException If there is an issue parsing the task data.
     */
    public static Task parseTask(String taskData) throws FileNotFoundException, DukeException {
        try {
            String taskType = taskData.substring(1, 2);
            boolean isDone = taskData.charAt(4) == 'X';
            String taskInfo = taskData.substring(7);
            Task taskToAdd = null;

            switch (taskType) {
            case "A":
                taskToAdd = new Add(taskInfo);
                break;
            case "T":
                taskToAdd = new ToDo(taskInfo);
                break;
            case "D":
                taskToAdd = createDeadLineTask(taskInfo);
                break;
            case "E":
                taskToAdd = createEventTask(taskInfo);
                break;
            default:
                throw new DukeException("File is corrupted!");
            }
            if (taskToAdd != null) {
                if (isDone) {
                    taskToAdd.isCompleted();
                }
                return taskToAdd;
            }
        } catch (Exception e) {
            return new Task("(CORRUPTED) " + taskData);
        }
        return null;
    }

    /**
     * Creates a DeadLine task based on task information.
     *
     * @param taskInfo The task information string containing the task description and deadline.
     * @return A DeadLine task instance based on the task information.
     * @throws DukeException If there is an issue parsing the task information.
     */
    private static Task createDeadLineTask(String taskInfo) throws DukeException {
        try {
            String[] parts = taskInfo.split(" \\(by: ");
            String description = parts[0];
            String by = parts[1].substring(0, parts[1].length() - 1);
            return new DeadLine(description, by);
        } catch (Exception e) {
            throw new DukeException("Error parsing Deadline task: " + taskInfo);
        }
    }

    /**
     * Creates an Event task based on task information.
     *
     * @param taskInfo The task information string containing the task description and event details.
     * @return An Event task instance based on the task information.
     * @throws DukeException If there is an issue parsing the task information.
     */
    private static Task createEventTask(String taskInfo) throws DukeException {
        try {
            String[] parts = taskInfo.split(" \\(from: ");
            String description = parts[0];
            String[] dateParts = parts[1].split(" to: ");
            String from = dateParts[0];
            String to = dateParts[1].substring(0, dateParts[1].length() - 1);
            return new Event(description, from, to);
        } catch (Exception e) {
            throw new DukeException("Error parsing Event task: " + taskInfo);
        }
    }

    /**
     * Parses a user input string and creates the corresponding `Command` object.
     *
     * @param input The user input string.
     * @return The `Command` object created from the user input.
     * @throws DukeException If there is an issue parsing the user input.
     */
    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return createToDoCommand(input);
        case "add":
            return createAddCommand(input);
        case "deadline":
            return createDeadLineCommand(input);
        case "event":
            return createEventCommand(input);
        case "unmark":
            return createUnmarkCommand(input);
        case "mark":
            return createMarkCommand(input);
        case "echo":
            return createEchoCommand(input);
        case "delete":
            return createDeleteCommand(input);
        case "help":
            return new HelpCommand();
        case "find":
            return createFindCommand(input);
        case "stats":
            return new StatisticsCommand();
        default:
            throw new DukeException("I'm sorry, but I don't understand that command.");
        }
    }

    /**
     * Creates a ToDoCommand based on user input.
     *
     * @param input The user input string containing the command and description.
     * @return A ToDoCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static ToDoCommand createToDoCommand(String input) throws DukeException {
        try {
            String todoDesc = input.substring(5).trim();
            Task todoTask = new ToDo(todoDesc);
            return new ToDoCommand(todoTask);
        } catch (Exception e) {
            throw new DukeException("Error parsing ToDo command: " + input);
        }
    }

    /**
     * Creates an AddCommand based on user input.
     *
     * @param input The user input string containing the command and description.
     * @return An AddCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static AddCommand createAddCommand(String input) throws DukeException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new DukeException("Please specify a task to add.");
            }
            String addDesc = input.substring(4).trim();
            Task addTask = new Add(addDesc);
            return new AddCommand(addTask);
        } catch (Exception e) {
            throw new DukeException("Error parsing Add command: " + input);
        }
    }

    /**
     * Creates a DeadLineCommand based on user input.
     *
     * @param input The user input string containing the command and description.
     * @return A DeadLineCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static DeadLineCommand createDeadLineCommand(String input) throws DukeException {
        try {
            String deadlineDesc = input.substring(8).trim();
            if (!deadlineDesc.contains("/by")) {
                throw new DukeException("The deadline command should include '/by'.");
            }
            String[] deadlineParts = deadlineDesc.split("/by");
            String description = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            return new DeadLineCommand(description, by);
        } catch (Exception e) {
            throw new DukeException("Error parsing Deadline command: " + input);
        }
    }

    /**
     * Creates an EventCommand based on user input.
     *
     * @param input The user input string containing the command and event description.
     * @return An EventCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static EventCommand createEventCommand(String input) throws DukeException {
        try {
            String eventDesc = input.substring(5).trim();
            if (!eventDesc.contains("/from") || !eventDesc.contains("/to")) {
                throw new DukeException("The event command should include '/from' and '/to'.");
            }
            String[] eventParts = eventDesc.split("/from");
            String eventDescription = eventParts[0].trim();
            String[] dateParts = eventParts[1].split("/to");
            String from = dateParts[0].trim();
            String to = dateParts[1].trim();
            return new EventCommand(eventDescription, from, to);
        } catch (Exception e) {
            throw new DukeException("Error parsing Event command: " + input);
        }
    }

    /**
     * Creates a MarkCommand based on user input.
     *
     * @param input The user input string containing the command and task number to mark as done.
     * @return A MarkCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static MarkCommand createMarkCommand(String input) throws DukeException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new DukeException("Please specify a task number to mark as done.");
            }
            int taskNumberToMark = Integer.parseInt(words[1]) - 1;
            return new MarkCommand(taskNumberToMark);
        } catch (Exception e) {
            throw new DukeException("Error parsing Mark command: " + input);
        }
    }

    /**
     * Creates an UnmarkCommand based on user input.
     *
     * @param input The user input string containing the command and task number to unmark.
     * @return An UnmarkCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static UnmarkCommand createUnmarkCommand(String input) throws DukeException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new DukeException("Please specify a task number to unmark.");
            }
            int taskNumberToUnmark = Integer.parseInt(words[1]) - 1;
            return new UnmarkCommand(taskNumberToUnmark);
        } catch (Exception e) {
            throw new DukeException("Error parsing Unmark command: " + input);
        }
    }

    /**
     * Creates an EchoCommand based on user input.
     *
     * @param input The user input string containing the command and text for the echo.
     * @return An EchoCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static EchoCommand createEchoCommand(String input) throws DukeException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new DukeException("Please provide text for the echo command.");
            }
            String echoText = input.substring(5).trim();
            return new EchoCommand(echoText);
        } catch (Exception e) {
            throw new DukeException("Error parsing Echo command: " + input);
        }
    }

    /**
     * Creates a DeleteCommand based on user input.
     *
     * @param input The user input string containing the command and task index to delete.
     * @return A DeleteCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static DeleteCommand createDeleteCommand(String input) throws DukeException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new DukeException("Please provide me a task index to delete");
            }
            int taskNumToDel = Integer.parseInt(words[1]) - 1;
            return new DeleteCommand(taskNumToDel);
        } catch (Exception e) {
            throw new DukeException("Error parsing Delete command: " + input);
        }
    }

    /**
     * Creates a FindCommand based on user input.
     *
     * @param input The user input string containing the command and keyword for searching.
     * @return A FindCommand instance based on the user input.
     * @throws DukeException If there is an issue parsing the input.
     */
    private static FindCommand createFindCommand(String input) throws DukeException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new DukeException("Please provide a keyword for the find command.");
            }
            String keyword = input.substring(5).trim();
            return new FindCommand(keyword);
        } catch (Exception e) {
            throw new DukeException("Error parsing Find command: " + input);
        }
    }
}
