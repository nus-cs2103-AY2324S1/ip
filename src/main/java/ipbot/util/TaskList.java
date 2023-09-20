package ipbot.util;

import ipbot.model.Deadline;
import ipbot.model.Event;
import ipbot.model.Pair;
import ipbot.model.Task;
import ipbot.model.ToDo;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class to store the list of tasks and act as an interface for interacting with the list.
 */
public class TaskList {
    private static final String MISSING_ARGUMENT_FORMAT = "%s missing a /%s argument!";
    private static final String ARGUMENT_EMPTY_FORMAT = "/%s argument cannot be empty!";
    private static final String EMPTY_TASK_DESCRIPTION = "Task description cannot be empty!";
    private static final String LIST_TASKS_PREFIX = "Here is the list of tasks:\n";

    private List<Task> tasks;

    /**
     * Initialises the list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return The size of the list of tasks.
     */
    public int getTasksSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the list of tasks as a String.
     *
     * @return The list of tasks.
     */
    public String listTasks() {
        String taskListString = LIST_TASKS_PREFIX;
        for (int index = 0; index < tasks.size(); index++) {
            taskListString += Ui.taskListFormatString(tasks.get(index), index + 1);
        }
        return taskListString;
    }

    /**
     * Checks if a Task should be printed for a query date.
     *
     * @param task The task to check.
     * @param queryDate The date to check.
     * @return true if the Task should be printed, false otherwise.
     */
    private boolean printTaskForQueryDate(Task task, LocalDateTime queryDate) {
        if (task instanceof Event) {
            Event event = (Event) task;
            if (!event.isOngoing(queryDate)) {
                return false;
            }
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            if (!deadline.isDue(queryDate)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Returns a list of the deadlines that fall on the date of the given time,
     * and the events that are ongoing at that time, as a String.
     *
     * @param queryDate The time to query.
     * @return The list of tasks.
     */
    public String listTasks(LocalDateTime queryDate) {
        String taskListString = LIST_TASKS_PREFIX;
        for (int index = 0; index < tasks.size(); index++) {
            if (queryDate != null && !printTaskForQueryDate(tasks.get(index), queryDate)) {
                continue;
            }
            taskListString += Ui.taskListFormatString(tasks.get(index), index + 1);
        }
        return taskListString;
    }

    /**
     * Returns a list of tasks that contain a certain String in its description, as a String.
     *
     * @param findStr The String to search in the description.
     * @return The list of tasks.
     */
    public String listTasks(String findStr) {
        if (findStr == null || findStr.isEmpty()) {
            return "Please enter a search term";
        }
        String taskListString = LIST_TASKS_PREFIX;
        for (int index = 0; index < tasks.size(); index++) {
            Task currTask = tasks.get(index);
            if (currTask.getDescription().contains(findStr)) {
                taskListString += Ui.taskListFormatString(tasks.get(index), index + 1);
            }
        }
        return taskListString;
    }

    /**
     * Marks a task at a given index.
     *
     * @param index The index of the task to mark.
     * @return A Pair, consisting of the marked task, and a boolean representing whether the task was unmarked before.
     */
    public Pair<Task, Boolean> markTask(int index) {
        assert 0 <= index && index < tasks.size() : "Invalid index";
        boolean wasNotMarked = tasks.get(index).markDone();
        return new Pair<>(tasks.get(index), wasNotMarked);
    }

    /**
     * Unmarks a task at a given index.
     *
     * @param index The index of the task to unmark.
     * @return A Pair, consisting of the unmarked task, and a boolean representing whether the task was marked before.
     */
    public Pair<Task, Boolean> unmarkTask(int index) {
        assert 0 <= index && index < tasks.size() : "Invalid index";
        boolean wasNotUnmarked = tasks.get(index).unmarkDone();
        return new Pair<>(tasks.get(index), wasNotUnmarked);
    }

    /**
     * Handles logic for marking a task.
     *
     * @param isMark Whether we are marking or unmarking the task.
     * @param commandArgs The arguments passed to the command.
     * @return The output of the command.
     * @throws CommandArgumentException
     */
    public String handleMark(boolean isMark, Map<String, String> commandArgs) throws CommandArgumentException {
        int markIndex = Parser.checkIndexArg(commandArgs.get(""), this.getTasksSize());
        if (markIndex == -1) {
            String markString = (isMark ? "mark" : "unmark");
            throw new CommandArgumentException("Invalid task to " + markString + ": " + commandArgs.get(""));
        }
        Pair<Task, Boolean> taskMark = (isMark ? this.markTask(markIndex) : this.unmarkTask(markIndex));
        if (taskMark.getSecond()) {
            return Ui.markTaskString(taskMark.getFirst(), isMark);
        } else {
            return Ui.alreadyMarkedTaskString(taskMark.getFirst(), isMark);
        }
    }

    /**
     * Extracts an argument and checks that it exists.
     *
     * @param commandArgs The arguments passed to the command.
     * @param commandType The command that requires the argument.
     * @param arg The argument required.
     * @return The argument variable corresponding to the required argument.
     * @throws CommandArgumentException
     */
    private String getArgument(Map<String, String> commandArgs, String commandType, String arg)
            throws CommandArgumentException {
        if (commandArgs.get(arg) == null) {
            throw new CommandArgumentException(String.format(MISSING_ARGUMENT_FORMAT, commandType, arg));
        }
        if (commandArgs.get(arg).isEmpty()) {
            throw new CommandArgumentException(String.format(ARGUMENT_EMPTY_FORMAT, arg));
        }
        return commandArgs.get(arg);
    }

    /**
     * Extracts an optional argument and checks that it is valid.
     *
     * @param commandArgs The arguments passed to the command.
     * @param arg The argument to check.
     * @return The argument variable corresponding to the required argument.
     * null if it is not given.
     * @throws CommandArgumentException
     */
    private String getOptionalArgument(Map<String, String> commandArgs, String arg) throws CommandArgumentException {
        if (commandArgs.get(arg) == null) {
            return null;
        }
        if (commandArgs.get(arg).isEmpty()) {
            throw new CommandArgumentException(String.format(ARGUMENT_EMPTY_FORMAT, arg));
        }
        return commandArgs.get(arg);
    }

    /**
     * Adds a ToDo object to the list of tasks with the given arguments.
     *
     * @param commandArgs A Map consisting of the arguments for the ToDo object being added.
     * @return The ToDo object added.
     */
    public ToDo addToDoWithArgs(Map<String, String> commandArgs) throws CommandArgumentException {
        if (commandArgs.get("").isEmpty()) {
            throw new CommandArgumentException(EMPTY_TASK_DESCRIPTION);
        }
        ToDo toDo = new ToDo(commandArgs.get(""));
        tasks.add(toDo);
        return toDo;
    }

    /**
     * Adds a Deadline object to the list of tasks with the given arguments.
     * Throws an exception for empty arguments or missing "by" argument.
     *
     * @param commandArgs A Map consisting of the arguments for the Deadline object being added.
     * @return The Deadline object added.
     * @throws CommandArgumentException
     */
    public Deadline addDeadlineWithArgs(Map<String, String> commandArgs) throws CommandArgumentException {
        if (commandArgs.get("").isEmpty()) {
            throw new CommandArgumentException(EMPTY_TASK_DESCRIPTION);
        }
        String byArg = getArgument(commandArgs, "Deadline", "by");
        Deadline deadline = new Deadline(commandArgs.get(""), byArg);
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Adds an Event object to the list of tasks with the given arguments.
     * Throws an exception for empty arguments or missing "from" or "to" argument.
     *
     * @param commandArgs A Map consisting of the arguments for the Event object being added.
     * @return The Event object added.
     * @throws CommandArgumentException
     */
    public Event addEventWithArgs(Map<String, String> commandArgs) throws CommandArgumentException {
        if (commandArgs.get("").isEmpty()) {
            throw new CommandArgumentException(EMPTY_TASK_DESCRIPTION);
        }
        String fromArg = getArgument(commandArgs, "Event", "from");
        String toArg = getArgument(commandArgs, "Event", "to");
        Event event = new Event(commandArgs.get(""), fromArg, toArg);
        tasks.add(event);
        return event;
    }

    /**
     * Handles logic for deleting a task.
     *
     * @param commandArgs The arguments passed to the command.
     * @return The output of the command.
     * @throws CommandArgumentException
     */
    public String handleDelete(Map<String, String> commandArgs) throws CommandArgumentException {
        int deleteIndex = Parser.checkIndexArg(commandArgs.get(""), this.getTasksSize());
        if (deleteIndex == -1) {
            throw new CommandArgumentException("Invalid task to delete: " + commandArgs.get(""));
        }
        Task task = this.deleteTask(deleteIndex);
        return Ui.deletedItemString(task);
    }

    /**
     * Deletes a task at the given index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        assert 0 <= index && index < tasks.size() : "Invalid index";
        Task task = tasks.remove(index);
        return task;
    }

    /**
     * Converts a String to a positive integer.
     *
     * @param string The String to convert.
     * @param errorPrefix The error message to throw if the String is not a positive integer.
     * @param defVal The value to return if the String is null.
     * @return defVal if the String is null. Otherwise, the converted String.
     * @throws CommandArgumentException
     */
    private int strToPositiveInt(String string, String errorPrefix, int defVal) throws CommandArgumentException {
        if (string == null) {
            return defVal;
        }
        if (!string.matches("^\\d+$")) {
            throw new CommandArgumentException(errorPrefix + string);
        }
        return Integer.parseInt(string);
    }

    /**
     * Handles logic for recurring a task.
     *
     * @param commandArgs The arguments passed to the command.
     * @return The output of the command.
     * @throws CommandArgumentException
     */
    public String recurTask(Map<String, String> commandArgs) throws CommandArgumentException {
        int recurIndex = Parser.checkIndexArg(commandArgs.get(""), this.getTasksSize());
        if (recurIndex == -1) {
            throw new CommandArgumentException("Invalid task to recur: " + commandArgs.get(""));
        }
        String yearArg = getOptionalArgument(commandArgs, "year");
        int year = strToPositiveInt(yearArg, "Invalid number of years: ", 0);
        String monthArg = getOptionalArgument(commandArgs,"month");
        int month = strToPositiveInt(monthArg, "Invalid number of months: ", 0);
        String weekArg = getOptionalArgument(commandArgs, "week");
        int week = strToPositiveInt(weekArg, "Invalid number of weeks: ", 0);
        String dayArg = getOptionalArgument(commandArgs, "day");
        int day = strToPositiveInt(dayArg, "Invalid number of days: ", 0);
        if (year == 0 && month == 0 && week == 0 && day == 0) {
            throw new CommandArgumentException("Invalid frequency of recurrence");
        }
        String numTimesArg = getArgument(commandArgs, "Recurrent Task", "times");
        int numTimes = strToPositiveInt(numTimesArg, "Invalid number of times to recur: ", 0);
        Period period = Period.parse(String.format("P%dY%dM%dW%dD", year, month, week, day));
        Task task = tasks.get(recurIndex);
        for (int i = 0; i < numTimes; i++) {
            task = task.copy();
            task.translateTime(period);
            tasks.add(task);
        }
        return "Added recurrence of task " + task.toString() + " for frequency "
                + year + " years, " + month + " months, " + week + " weeks, " + day + " days, "
                + numTimes + " times";
    }

    /**
     * Converts this TaskList to a CSV String.
     *
     * @return The CSV String representation of this TaskList.
     */
    public String toCommaString() {
        String string = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            string += tasks.get(i).toCommaString() + "\n";
        }
        return string;
    }
}
