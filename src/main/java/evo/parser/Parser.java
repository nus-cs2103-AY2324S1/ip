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
    public Command parse(String fullCommand) {
        try {
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
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            // Catch the exception when the user types in an invalid date and time
            System.out.println("Please type in a valid date/time input. Eg: event project meeting /from "
                    + "2019-12-15 1800 /to 2000\n");
        } catch (InvalidDateInputException invalidDateInputException) {
            // Catch the exception when the user types in an invalid date
            System.out.println("Please type in a valid date input. Eg: deadline return book /from "
                    + "2019-12-15 1800\n");
        } catch (InvalidInputException invalidInputException) {
            // Catch the exception when the user types in an invalid integer after the command
            System.out.println("Please type in a valid integer after the command.\n");
        } catch (MissingDescriptionAndDeadlineException missingDescriptionAndDeadlineException) {
            // Catch the exception when the description and deadline of Deadline object are missing
            System.out.println("Description and deadline of this task are missing. "
                    + "Please specify the description and the deadline of this task.\n");
        } catch (MissingDeadlineException missingDeadlineException) {
            // Catch the exception when the deadline of Deadline object are missing
            System.out.println("Deadline of this task are missing. Please specify the deadline of this task.\n");
        } catch (MissingDescriptionAndDurationException missingDescAndDurationExp) {
            // Catch the exception when the description and duration of Event object are missing
            System.out.println("Description and duration of this event are missing. "
                    + "Please specify the description and the duration of this event.\n");
        } catch (MissingDurationException missingDurationException) {
            // Catch the exception when the duration of Event object is missing
            System.out.println("Duration of this event is incomplete. "
                    + "Please specify the start date/time and/or end date/time.\n");
        } catch (MissingTaskToDeleteException missingTaskToDeleteException) {
            // Catch the exception when user never specifies which task to be deleted
            System.out.println("Please specify the task to be deleted.\n");
        } catch (MissingTaskToFindException missingTaskToFindException) {
            // Catch the exception when user never specifies a keyword to find a task
            System.out.println("Please specify a keyword to find a task.\n");
        } catch (MissingTaskToMarkException missingTaskToMarkException) {
            // Catch the exception when user never specifies which task to be marked
            System.out.println("Please specify the task to be marked.\n");
        } catch (MissingTaskToUnmarkException missingTaskToUnmarkException) {
            // Catch the exception when user never specifies which task to be unmarked
            System.out.println("Please specify the task to be unmarked.\n");
        } catch (MissingToDoDescriptionException missingToDoDescriptionException) {
            // Catch the exception when the description of ToDo task is missing
            System.out.println("Description of this task is missing. "
                    + "Please specify the description of this task.\n");
        } catch (NoTaskDeleteException noTaskDeleteException) {
            // Catch the exception when user tries to delete task from an empty taskList
            System.out.println("This task cannot be deleted as there is no task in the list.\n");
        } catch (NoTaskFindException noTaskFindException) {
            // Catch the exception when user tries to find task from an empty taskList
            System.out.println("No task can be found as there is no task in the list.\n");
        } catch (NoSuchTaskException noSuchTaskException) {
            // Catch the exception when the task to be marked/unmarked does not exist.
            System.out.println("The task to be marked/unmarked does not exist.\n");
        } catch (NoSuchTaskDeleteException noSuchTaskDeleteException) {
            // Catch the exception when the task to be deleted does not exist.
            System.out.println("The task to be deleted does not exist.\n");
        } catch (InvalidOperationException invalidOperationException) {
            // Catch the exception when the operation is invalid
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        return null;
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
