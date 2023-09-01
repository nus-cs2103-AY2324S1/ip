package evo.parser;

import java.util.Objects;

import evo.commands.*;
import evo.exceptions.*;
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

            String[] typeAndDates = fullCommand.split("/");
            String[] actionAndDesc = typeAndDates[0].split(" ");
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
                if (tasksList.tasksListLength() < Integer.parseInt(actionType[1]) || Integer.parseInt(actionType[1]) <= 0) {
                    throw new NoSuchTaskException();
                }
                return new MarkCommand(actionType[1]);
            } else if (Objects.equals(actionType[0], "unmark")) {
                // Unmark Command
                if (actionType.length == 1) {
                    throw new MissingTaskToUnmarkException();
                }
                if (tasksList.tasksListLength() < Integer.parseInt(actionType[1]) || Integer.parseInt(actionType[1]) <= 0) {
                    throw new NoSuchTaskException();
                }
                return new UnmarkCommand(actionType[1]);
            } else if (Objects.equals(actionType[0], "delete")) {
                // Delete Command
                if (tasksList.isEmpty()) {
                    throw new NoTaskException();
                }
                if (actionType.length == 1) {
                    throw new MissingTaskToDeleteException();
                }
                if (tasksList.tasksListLength() < Integer.parseInt(actionType[1]) || Integer.parseInt(actionType[1]) <= 0) {
                    throw new NoSuchTaskDeleteException();
                }
                return new DeleteCommand(actionType[1]);
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
                return new DeadlineCommand(actionAndDesc, typeAndDates);
            } else if (Objects.equals(actionType[0], "event")) {
                // Adds event Command
                if (actionType.length == 1) {
                    throw new MissingDescriptionAndDurationException();
                }
                if (!fullCommand.contains("/from") || !fullCommand.contains("/to")) {
                    throw new MissingDurationException();
                }
                if (count != 2) {
                    throw new InvalidDateAndTimeInputException();
                }
                return new EventCommand(actionAndDesc, typeAndDates);
            } else {
                // Invalid command
                throw new InvalidOperationException();
            }
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            // Catch the exception when the user types in an invalid date and time
            System.out.println("Please type in a valid date/time input. Eg: event project meeting /from " +
                    "2019-12-15 1800 /to 2000\n");
        } catch (InvalidDateInputException invalidDateInputException) {
            // Catch the exception when the user types in an invalid date
            System.out.println("Please type in a valid date input. Eg: deadline return book /from " +
                    "2019-12-15 1800\n");
        } catch (MissingDescriptionAndDeadlineException missingDescriptionAndDeadlineException) {
            // Catch the exception when the description and deadline of Deadline object are missing
            System.out.println("Description and deadline of this task are missing. " +
                    "Please specify the description and the deadline of this task.\n");
        } catch (MissingDeadlineException missingDeadlineException) {
            // Catch the exception when the deadline of Deadline object are missing
            System.out.println("Deadline of this task are missing. Please specify the deadline of this task.\n");
        } catch (MissingDescriptionAndDurationException missingDescAndDurationExp) {
            // Catch the exception when the description and duration of Event object are missing
            System.out.println("Description and duration of this event are missing. " +
                    "Please specify the description and the duration of this event.\n");
        } catch (MissingDurationException missingDurationException) {
            // Catch the exception when the duration of Event object is missing
            System.out.println("Duration of this event is incomplete. " +
                    "Please specify the start date/time and/or end date/time.\n");
        } catch (MissingTaskToDeleteException missingTaskToDeleteException) {
            // Catch the exception when user never specifies which task to be deleted
            System.out.println("Please specify the task to be deleted.\n");
        } catch (MissingTaskToMarkException missingTaskToMarkException) {
            // Catch the exception when user never specifies which task to be marked
            System.out.println("Please specify the task to be marked.\n");
        } catch (MissingTaskToUnmarkException missingTaskToUnmarkException) {
            // Catch the exception when user never specifies which task to be unmarked
            System.out.println("Please specify the task to be unmarked.\n");
        } catch (MissingToDoDescriptionException missingToDoDescriptionException) {
            // Catch the exception when the description of ToDo task is missing
            System.out.println("Description of this task is missing. " +
                    "Please specify the description of this task.\n");
        } catch (NoTaskException noTaskException) {
            // Catch the exception when user tries to delete task from an empty taskList
            System.out.println("This task cannot be deleted as there is no task in the list.\n");
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
}
