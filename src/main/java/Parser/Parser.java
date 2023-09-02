package parser;

import java.io.FileNotFoundException;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.DeadLineCommand;
import command.DeleteCommand;
import command.EchoCommand;
import command.EventCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
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
                taskInfo = taskInfo.substring(0, taskInfo.indexOf("(") - 1);
                String xtraInfo = taskData.substring(taskData.indexOf("(") + 1, taskData.indexOf(")"));
                String[] deadLineInfo = xtraInfo.split(": ");
                String by = deadLineInfo[1];
                taskToAdd = new DeadLine(taskInfo, by);
                break;
            case "E":
                taskInfo = taskInfo.substring(0, taskInfo.indexOf('(') - 1);
                String addInfo = taskData.substring(taskData.indexOf('(') + 1, taskData.indexOf(')'));
                String[] eventInfo = addInfo.split(": ");
                String from = eventInfo[1].substring(0, eventInfo[1].length() - 2).trim();
                String to = eventInfo[2].trim();
                taskToAdd = new Event(taskInfo, from, to);
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
     * Parses a user input string and creates the corresponding `Command` object.
     *
     * @param input The user input string.
     * @return The `Command` object created from the user input.
     * @throws DukeException If there is an issue parsing the user input.
     */
    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ");
        String command = words[0].toLowerCase();

        if (words.length < 2 && !command.equals("bye") && !command.equals("list") && !command.equals("help")) {
            throw new DukeException("The description of the command cannot be empty.");
        }

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            String todoDesc = input.substring(5).trim(); // Extract the description
            Task todoTask = new ToDo(todoDesc);
            return new ToDoCommand(todoTask);
        case "add":
            String addDesc = input.substring(4).trim();
            Task addTask = new ToDo(addDesc);
            return new AddCommand(addTask);
        case "deadline":
            String deadlineDesc = input.substring(8).trim(); // Extract the description
            if (!deadlineDesc.contains("/by")) {
                throw new DukeException("The deadline command should include '/by'.");
            }
            String[] deadlineParts = deadlineDesc.split("/by");
            String description = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            return new DeadLineCommand(description, by);
        case "event":
            String eventDesc = input.substring(5).trim(); // Extract the description
            if (!eventDesc.contains("/from") || !eventDesc.contains("/to")) {
                throw new DukeException("The event command should include '/from' and '/to'.");
            }
            String[] eventParts = eventDesc.split("/from");
            String eventDescription = eventParts[0].trim();
            String[] dateParts = eventParts[1].split("/to");
            String from = dateParts[0].trim();
            String to = dateParts[1].trim();
            return new EventCommand(eventDescription, from, to);
        case "unmark":
            if (words.length < 2) {
                throw new DukeException("Please specify a task number to unmark.");
            }
            int taskNumberToUnmark = Integer.parseInt(words[1]) - 1; // Assuming tasks are 1-based
            return new UnmarkCommand(taskNumberToUnmark);
        case "mark":
            if (words.length < 2) {
                throw new DukeException("Please specify a task number to mark as done.");
            }
            int taskNumberToMark = Integer.parseInt(words[1]) - 1; // Assuming tasks are 1-based
            return new MarkCommand(taskNumberToMark);
        case "echo":
            if (words.length < 2) {
                throw new DukeException("Please provide text for the echo command.");
            }
            String echoText = input.substring(5).trim(); // Extract the text
            return new EchoCommand(echoText);
        case "delete":
            int taskNumToDel = Integer.parseInt(words[1]) - 1;
            return new DeleteCommand(taskNumToDel);
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("I'm sorry, but I don't understand that command.");
        }
    }
}
