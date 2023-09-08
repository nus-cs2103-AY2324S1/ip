package evo.parser;

import java.util.Objects;

import evo.commands.ByeCommand;
import evo.commands.Command;
import evo.commands.DeadlineCommand;
import evo.commands.DeleteCommand;
import evo.commands.EventCommand;
import evo.commands.FindCommand;
import evo.commands.ListCommand;
import evo.commands.MarkCommand;
import evo.commands.ToDoCommand;
import evo.commands.UnmarkCommand;
import evo.exceptions.InvalidDateAndTimeInputException;
import evo.exceptions.InvalidDateInputException;
import evo.exceptions.InvalidInputException;
import evo.exceptions.InvalidOperationException;
import evo.exceptions.MissingDeadlineException;
import evo.exceptions.MissingDescriptionAndDeadlineException;
import evo.exceptions.MissingDescriptionAndDurationException;
import evo.exceptions.MissingDurationException;
import evo.exceptions.MissingTaskToDeleteException;
import evo.exceptions.MissingTaskToFindException;
import evo.exceptions.MissingTaskToMarkException;
import evo.exceptions.MissingTaskToUnmarkException;
import evo.exceptions.MissingToDoDescriptionException;
import evo.exceptions.NoSuchTaskDeleteException;
import evo.exceptions.NoSuchTaskException;
import evo.exceptions.NoTaskDeleteException;
import evo.exceptions.NoTaskFindException;
import evo.tasks.TaskList;

/**
 * The Parser class is responsible for parsing user commands and converting them into executable commands.
 * It processes user input, validates commands, and generates corresponding Command objects.
 */
public class Parser {

    /**
     * The TaskList containing tasks to be managed.
     */
    protected TaskList tasksList;

    /**
     * Constructs a Parser object with the specified TaskList.
     *
     * @param tasksList The TaskList containing tasks to be managed.
     */
    public Parser(TaskList tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Parses a user command and generates a corresponding Command object.
     *
     * @param fullCommand The user command or input as a string.
     * @return A Command object corresponding to the user's input command.
     */
    public Command parse(String fullCommand) throws Exception {
//        try {
            String[] actionType = fullCommand.split(" ");

            String[] typeAndDate = fullCommand.split("/");
            String[] actionAndDesc = typeAndDate[0].split(" ");
            int count = 0;
            char target = '/';
            for (int i = 0; i < fullCommand.length(); i++) {
                if (fullCommand.charAt(i) == target) {
                    count++;
                }
            }
            if (Objects.equals(fullCommand, "bye")) {
                // Bye Command
                return new ByeCommand();
            } else if (Objects.equals(fullCommand, "list")) {
                // List Command
                return new ListCommand();
            } else if (Objects.equals(actionType[0], "mark")) {
                // Mark Command
                if (actionType.length == 1) {
                    throw new MissingTaskToMarkException();
                }
                if (!isInteger(actionType[1])) {
                    throw new InvalidInputException("Please type in a valid integer after the command.");
                }
                if (tasksList.tasksListLength() < Integer.parseInt(actionType[1])
                        || Integer.parseInt(actionType[1]) <= 0) {
                    throw new NoSuchTaskException();
                }
                return new MarkCommand(actionType[1]);
            } else if (Objects.equals(actionType[0], "unmark")) {
                // Unmark Command
                if (actionType.length == 1) {
                    throw new MissingTaskToUnmarkException();
                }
                if (!isInteger(actionType[1])) {
                    throw new InvalidInputException("Please type in a valid integer after the command.");
                }
                if (tasksList.tasksListLength() < Integer.parseInt(actionType[1])
                        || Integer.parseInt(actionType[1]) <= 0) {
                    throw new NoSuchTaskException();
                }
                return new UnmarkCommand(actionType[1]);
            } else if (Objects.equals(actionType[0], "delete")) {
                // Delete Command
                if (tasksList.isEmpty()) {
                    throw new NoTaskDeleteException();
                }
                if (actionType.length == 1) {
                    throw new MissingTaskToDeleteException();
                }
                if (!isInteger(actionType[1])) {
                    throw new InvalidInputException("Please type in a valid integer after the command.");
                }
                if (tasksList.tasksListLength() < Integer.parseInt(actionType[1])
                        || Integer.parseInt(actionType[1]) <= 0) {
                    throw new NoSuchTaskDeleteException();
                }
                return new DeleteCommand(actionType[1]);
            } else if (Objects.equals(actionType[0], "find")) {
                // Find Command
                if (tasksList.isEmpty()) {
                    throw new NoTaskFindException("No task can be found as there is no task in the list.");
                }
                if (actionType.length == 1) {
                    throw new MissingTaskToFindException("Please specify a keyword to find a task.");
                }
                return new FindCommand(actionType[1]);
            } else if (Objects.equals(actionType[0], "todo")) {
                // Adds to do Command
                if (actionType.length == 1) {
                    throw new MissingToDoDescriptionException();
                }
                return new ToDoCommand(actionType);
            } else if (Objects.equals(actionType[0], "deadline")) {
                // Adds deadline Command
                if (actionType.length == 1) {
                    throw new MissingDescriptionAndDeadlineException();
                }
                if (!fullCommand.contains("/by")) {
                    throw new MissingDeadlineException();
                }
                if (count != 1) {
                    throw new InvalidDateInputException();
                }
                return new DeadlineCommand(actionAndDesc, typeAndDate);
            } else if (Objects.equals(actionType[0], "event")) {
                // Adds event Command
                if (actionType.length == 1) {
                    throw new MissingDescriptionAndDurationException();
                }
                if (!fullCommand.contains("/from") || !fullCommand.contains("/to")) {
                    throw new MissingDurationException();
                }
                if (count != 2) {
                    throw new InvalidDateAndTimeInputException("Please type in a valid date/time input. Eg: event"
                            + " project meeting /from 2019-12-15 1800 /to 2000\n");
                }
                return new EventCommand(actionAndDesc, typeAndDate);
            } else {
                // Invalid command
                throw new InvalidOperationException();
            }
    }

    /**
     * Checks if a given string represents an integer value.
     *
     * @param input A string that may or may not represent an integer.
     * @return true if the input string represents an integer, false otherwise.
     */
    public boolean isInteger(String input) {
        try {
            int value = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException numberFormatException) {
            System.out.println(input + " is not an integer.");
        }
        return false;
    }
}
