package seedu.dookie;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Encapsulates the Parser class.
 * The Parser deals with the control flow of the program depending on the user's input command.
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private enum TaskType {LIST, TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, FIND, POSTPONE, BYE};

    /**
     * Creates a Parser instance.
     *
     * @param storage The Storage used for the Dookie program.
     * @param tasks The TaskList created for the Dookie program.
     * @param ui The Ui used by the Dookie program.
     */
    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;

    }

    /**
     * Executes the necessary processes based on the command inputted by the user.
     *
     * @param cmd The command inputted by the user.
     */
    public String parse(String cmd) {
        try {
            // Try to convert the task type to an enum. If does not match, throw
            // InvocationTargetException.
            TaskType taskType = TaskType.valueOf(getTaskType(cmd));

            switch (taskType) {
                case LIST:
                    return new ListCommand(tasks, ui).execute();
                case TODO:
                    return new ToDoCommand(this, tasks, ui).execute(cmd);
                case DEADLINE:
                    return new DeadlineCommand(this, tasks, ui).execute(cmd);
                case EVENT:
                    return new EventCommand(this, tasks, ui).execute(cmd);
                case DELETE:
                    return new DeleteCommand(this, tasks, ui).execute(cmd);
                case MARK:
                    return new MarkCommand(this, tasks, ui).execute(cmd);
                case UNMARK:
                    return new UnmarkCommand(this, tasks, ui).execute(cmd);
                case FIND:
                    return new FindCommand(this, tasks, ui).execute(cmd);
                case POSTPONE:
                    return new PostponeCommand(this, tasks, ui).execute(cmd);
                case BYE:
                    return new ExitCommand(ui).execute();
            }
        } catch (DookieException e) {
            return e.getMessage();
        } catch (AssertionError e) {
            return "Assertion failed: " + e.getMessage();
        } catch (Exception e) { // This catch block catches InvocationTargetException from the
                                // TaskType.valueOf() method
            return new TaskTypeException().getMessage();
        }
        return null;
    }

    /**
     * Returns a string representing the first word of the user input in uppercase.
     *
     * @param cmd The user input.
     * @return A string representing the first word of the user input in uppercase.
     * @throws TaskTypeException if the user input is empty.
     */
    private String getTaskType(String cmd) throws TaskTypeException {
        try {
            return cmd.split(" ", 2)[0].toUpperCase();
        } catch (Exception e) {
            throw new TaskTypeException();
        }
    }


    /**
     * Checks whether the task description has been specified.
     *
     * @param cmd The command inputted by the user.
     * @return True if there is no task description specified.
     */
    public boolean descriptionIsEmpty(String cmd) {
        return cmd.split(" ").length == 1;
    }

    /**
     * Checks whether the input task has no deadline.
     * A task has no deadline if there is no "/" followed by at least one character.
     *
     * @param taskWithDeadline The task name and deadline given by the user.
     * @return True if the task has no specified deadline.
     */
    public boolean hasNoDeadline(String taskWithDeadline) {
        return taskWithDeadline.split("/").length == 1;
    }

    /**
     * Checks whether the input deadline is a proper date and time.
     *
     * @param deadline The deadline specified by the user.
     * @return A LocalDateTime object containing the date and time given, if it is a valid deadline. Else, return null.
     */
    public LocalDateTime checkDeadline(String deadline) {
        String[] parts = deadline.split(" ");
        try {
            // If there isn't exactly three components in the deadline, return false
            if (parts.length != 3) {
                throw new InvalidDeadlineException(deadline);
            }

            String by = parts[0];
            String date = parts[1];
            String time = parts[2];

            // If the first word is not "by", return false
            if (!by.equals("by")) {
                throw new InvalidDeadlineException(deadline);
            }

            String[] dateParts = date.split("-");
            // If the date does not have three components, return false
            if (dateParts.length != 3) {
                throw new InvalidDeadlineException(deadline);
            }

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // If time is not a four digit number, return false
            if (time.length() != 4) {
                throw new InvalidDeadlineException(deadline);
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2, 4));

            return LocalDateTime.of(year, month, day, hour, min);
        } catch (InvalidDeadlineException e) {
            return null;
        }
    }

    /**
     * Checks whether the given start date and time for an event is valid.
     *
     * @param starting The start date and time
     * @return A LocalDateTime representing the date and time
     */
    public LocalDateTime checkStarting(String starting ){
        String[] parts = starting.split(" ");
        try {
            // If there isn't exactly three components in the deadline, return false
            if (parts.length != 3) {
                throw new InvalidDurationException(starting);
            }

            String from = parts[0];
            String date = parts[1];
            String time = parts[2];

            // If the first word is not "by", return false
            if (!from.equals("from")) {
                throw new InvalidDurationException(starting);
            }

            String[] dateParts = date.split("-");
            // If the date does not have three components, return false
            if (dateParts.length != 3) {
                throw new InvalidDurationException(starting);
            }

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // If time is not a four digit number, return false
            if (time.length() != 4) {
                throw new InvalidDurationException(starting);
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2, 4));

            return LocalDateTime.of(year, month, day, hour, min);
        } catch (InvalidDurationException e) {
            return null;
        }
    }

    /**
     * Checks whether the given end date and time for an event is valid.
     *
     * @param ending The end date and time
     * @return A LocalDateTime representing the date and time
     */
    public LocalDateTime checkEnding(String ending) {
        String[] parts = ending.split(" ");
        try {
            // If there isn't exactly three components in the deadline, return false
            if (parts.length != 3) {
                throw new InvalidDurationException(ending);
            }

            String to = parts[0];
            String date = parts[1];
            String time = parts[2];

            // If the first word is not "by", return false
            if (!to.equals("to")) {
                throw new InvalidDurationException(ending);
            }

            String[] dateParts = date.split("-");
            // If the date does not have three components, return false
            if (dateParts.length != 3) {
                throw new InvalidDurationException(ending);
            }

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // If time is not a four digit number, return false
            if (time.length() != 4) {
                throw new InvalidDurationException(ending);
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2, 4));

            return LocalDateTime.of(year, month, day, hour, min);
        } catch (InvalidDurationException e) {
            return null;
        }
    }

    /**
     * Checks whether a task with the given task number argument exists.
     *
     * @param number The integer specified by the user.
     * @return True if a task with that task number exists.
     */
    //
    public boolean isValidTaskNumber(int number) {
        try {
            int listSize = storage.load().size();
            return number > 0 && number <= listSize;
        } catch (InvalidDataFormatException e) {
            return false;
        }
    }
}