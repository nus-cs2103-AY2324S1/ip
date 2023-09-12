package seedu.duke;

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
     * @param storage The Storage used for the Duke program.
     * @param tasks The TaskList created for the Duke program.
     * @param ui The Ui used by the Duke program.
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
            TaskType taskType = TaskType.valueOf(getTaskType(cmd));

            if (taskType == TaskType.LIST) {
                return getListItems();
            } else if (taskType == TaskType.TODO) {
                return parseToDoCommand(cmd);
            } else if (taskType == TaskType.DEADLINE) {
                return parseDeadlineCommand(cmd);
            } else if (taskType == TaskType.EVENT) {
                return parseEventCommand(cmd);
            } else if (taskType == TaskType.DELETE) {
                return parseDeleteCommand(cmd);
            } else if (taskType == TaskType.MARK) {
                return parseMarkCommand(cmd);
            } else if (taskType == TaskType.UNMARK) {
                return parseUnmarkCommand(cmd);
            } else if (taskType == TaskType.FIND) {
                return parseFindCommand(cmd);
            } else if (taskType == TaskType.POSTPONE) {
                return parsePostponeCommand(cmd);
            } else if (taskType == TaskType.BYE) {
                return exit();
            } else {  // If the inputted command is not valid, throw TaskTypeException
                throw new TaskTypeException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (AssertionError e) {
            return "Assertion failed: " + e.getMessage();
        }
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
     * Returns a string containing the current tasks.
     *
     * @return A string containing the current tasks.
     */
    private String getListItems() {
        return ui.printListItems(tasks);
    }

    /**
     * Executes the code corresponding to a ToDo command.
     *
     * @param cmd The user input.
     * @return A string containing the add task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     */
    private String parseToDoCommand(String cmd) throws InvalidDescriptionException {
        // Check if description is empty
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("todo");
        }

        String taskName = cmd.split(" ", 2)[1];
        Task todo = new ToDo(taskName);
        tasks.addTask(todo);
        return ui.printAddTaskMessage(todo, tasks);
    }

    /**
     * Executes the code corresponding to a Deadline command.
     *
     * @param cmd The user input.
     * @return A string containing the add task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws NoDeadlineException When there is no deadline specified.
     * @throws InvalidDeadlineException When the deadline specified is not in the correct format.
     */
    private String parseDeadlineCommand(String cmd) throws InvalidDescriptionException,
                                                            NoDeadlineException,
                                                            InvalidDeadlineException {
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("deadline");
        }

        String taskWithDeadline = cmd.split(" ", 2)[1];

        if (hasNoDeadline(taskWithDeadline)) {
            throw new NoDeadlineException();
        }

        String taskName = taskWithDeadline.split("/", 2)[0];
        String deadlineDescription = taskWithDeadline.split("/", 2)[1];

        System.out.println("I am about to check the deadline");
        LocalDateTime dateTime = checkDeadline(deadlineDescription);
        System.out.println("I have obtained a deadline");
        if (dateTime == null) {
            throw new InvalidDeadlineException(deadlineDescription);
        }
        Task deadline = new Deadline(taskName, dateTime);
        tasks.addTask(deadline);
        return ui.printAddTaskMessage(deadline, tasks);
    }

    /**
     * Executes the code corresponding to a Deadline command.
     *
     * @param cmd The user input.
     * @return A string containing the add task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws IncompleteDurationException When the duration is not correctly specified.
     */
    private String parseEventCommand(String cmd) throws InvalidDescriptionException,
                                                        IncompleteDurationException {
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("event");
        }

        String taskWithDuration = cmd.split(" ", 2)[1];
        String[] time = taskWithDuration.split("/");

        // Check if there is a valid duration
        if (time.length != 3) {
            throw new IncompleteDurationException();
        }

        String taskName = time[0];
        String starting = time[1];
        String ending = time[2];

        // Assumes that starting and ending both start with "from" and "to" respectively
        Task event = new Event(taskName, checkStarting(starting), checkEnding(ending));
        tasks.addTask(event);
        return ui.printAddTaskMessage(event, tasks);
    }

    /**
     * Executes the code corresponding to a delete command.
     *
     * @param cmd The user input.
     * @return A string containing the delete task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws InvalidIntegerException When the argument specified is not an integer.
     * @throws InvalidTaskNumberException When the integer specified is not a task number.
     * @throws InvalidDataFormatException When the format duke.txt file is incorrect.
     */
    private String parseDeleteCommand(String cmd) throws InvalidDescriptionException,
                                                            InvalidIntegerException,
                                                            InvalidTaskNumberException,
                                                            InvalidDataFormatException {
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("delete");
        }

        int taskNumber = -1;
        String integer = cmd.split(" ", 2)[1];

        try {
            taskNumber = Integer.parseInt(integer);
        } catch (Exception e) {
            throw new InvalidIntegerException();
        }

        if (!isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(taskNumber);
        }

        Task task = tasks.updateTasks(taskNumber - 1, "DELETE");
        return ui.printDeleteTaskMessage(task, new TaskList(storage.load()));
    }

    /**
     * Executes the code corresponding to a mark command.
     *
     * @param cmd The user input.
     * @return A string containing the mark task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws InvalidIntegerException When the argument specified is not an integer.
     * @throws InvalidTaskNumberException When the integer specified is not a task number.
     * @throws InvalidDataFormatException When the format duke.txt file is incorrect.
     */
    private String parseMarkCommand(String cmd) throws InvalidDescriptionException,
                                                        InvalidIntegerException,
                                                        InvalidTaskNumberException,
                                                        InvalidDataFormatException {
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("mark");
        }

        int taskNumber = -1;
        String integer = cmd.split(" ", 2)[1];

        try {
            taskNumber = Integer.parseInt(integer);
        } catch (Exception e) {
            throw new InvalidIntegerException();
        }

        if (!isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(taskNumber);
        }

        Task task = tasks.updateTasks(taskNumber - 1, "MARK");
        return ui.printMarkedTaskMessage(task);
    }

    /**
     * Executes the code corresponding to an unmark command.
     *
     * @param cmd The user input.
     * @return A string containing the unmark task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws InvalidIntegerException When the argument specified is not an integer.
     * @throws InvalidTaskNumberException When the integer specified is not a task number.
     * @throws InvalidDataFormatException When the format duke.txt file is incorrect.
     */
    private String parseUnmarkCommand(String cmd) throws InvalidDescriptionException,
                                                            InvalidIntegerException,
                                                            InvalidTaskNumberException,
                                                            InvalidDataFormatException {
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("unmark");
        }

        int taskNumber = -1;
        String integer = cmd.split(" ", 2)[1];

        try {
            taskNumber = Integer.parseInt(integer);
        } catch (Exception e) {
            throw new InvalidIntegerException();
        }

        if (!isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(taskNumber);
        }

        Task task = tasks.updateTasks(taskNumber - 1, "UNMARK");
        return ui.printUnmarkedTaskMessage(task);
    }

    /**
     * Executes the code corresponding to a find command.
     *
     * @param cmd The user input.
     * @return A string containing the found results.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     */
    private String parseFindCommand(String cmd) throws InvalidDescriptionException {
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("find");
        }

        String keyword = cmd.split(" ", 2)[1];
        ArrayList<Task> results = tasks.find(keyword);
        return ui.printFindResults(results);
    }

    /**
     * Returns a string containing the exit message.
     *
     * @return A string containing the exit message.
     */
    private String exit() {
        return ui.printExit();
    }

    /**
     * Executes the code corresponding to a postpone command.
     *
     * @param cmd The user input.
     * @return A string containing the postpone task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws InvalidIntegerException When the argument specified is not an integer.
     * @throws InvalidTaskNumberException When the integer specified is not a task number.
     * @throws InvalidDataFormatException When the format duke.txt file is incorrect.
     * @throws NoDeadlineException When no new deadline is specified.
     */
    private String parsePostponeCommand(String cmd) throws InvalidDescriptionException,
                                                            InvalidIntegerException,
                                                            InvalidTaskNumberException,
                                                            InvalidDataFormatException,
                                                            NoDeadlineException {
        if (descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("postpone");
        }

        int taskNumber = -1;
        String integer = cmd.split(" ", 3)[1];

        try {
            taskNumber = Integer.parseInt(integer);
        } catch (Exception e) {
            throw new InvalidIntegerException();
        }

        if (!isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(taskNumber);
        }

        Task task = tasks.postponeTask(cmd,(taskNumber - 1));
        return ui.printPostponeMessage(task);

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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return false;
        }
    }
}
