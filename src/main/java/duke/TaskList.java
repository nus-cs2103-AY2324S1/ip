package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;

import duke.messages.Messages;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles all operations of the taskList.
 */

public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Constructor
     * @param tasks a list of tasks from the previous usage.
     */
    public TaskList(ArrayList<String> tasks) {
        this.taskList = new ArrayList<>();
        for (String task: tasks) {
            if (task.contains("T")) {
                this.taskList.add(parseToDoTask(task));
            } else if (task.contains("D")) {
                this.taskList.add(parseDeadlineTask(task));
            } else if (task.contains("E")) {
                this.taskList.add(parseEventTask(task));
            }
        }
    }

    /**
     * Convert a Deadline task description into an instance of the Deadline Class.
     * @param taskDescription description of Deadline task
     * @return Deadline
     */
    private Deadline parseDeadlineTask(String taskDescription) {
        String[] deadlineString = taskDescription.substring(8).split("\\(");
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .appendPattern(Messages.DATE_FORMAT.getMessage()).toFormatter(Locale.ENGLISH);
        LocalDateTime deadlineDate = LocalDateTime.parse(deadlineString[1]
                .substring(4, deadlineString[1].length() - 1).trim(), formatter);
        return new Deadline(deadlineString[0].trim(), deadlineDate);
    }

    /**
     * Convert an Event task description into an instance of the Event Class.
     * @param taskDescription description of Event task
     * @return Event
     */
    private Event parseEventTask(String taskDescription) {
        String[] eventString = taskDescription.substring(8).split("\\(");
        String[] timelineString = eventString[1].split("to:");
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .appendPattern(Messages.DATE_FORMAT.getMessage()).toFormatter(Locale.ENGLISH);
        LocalDateTime startDate = LocalDateTime.parse(timelineString[0]
                .substring(6).trim(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(timelineString[1].substring(0,
                timelineString[1].length() - 1).trim(), formatter);
        return new Event(eventString[0].trim(), startDate, endDate);
    }

    /**
     * Convert a ToDo task description into an instance of the Event Class
     * @param taskDescription description of ToDo task
     * @return ToDo
     */
    private Todo parseToDoTask(String taskDescription) {
        return new Todo(taskDescription.substring(8));
    }

    /**
     * Obtain the task list in arraylist format.
     * @return the ArrayList
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Get the length of the task list
     * @return an integer of the list's length
     */
    public int getLengthOfTaskList() {
        return this.taskList.size();
    }

    /**
     * Obtain a specific task from the list
     * @param taskNumber the index of the task in the list
     * @return the specified task
     */
    public Task getTask(int taskNumber) {
        return this.taskList.get(taskNumber);
    }

    /**
     * Adds a task to the list.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the specified task from the task list.
     * @param taskNumber the index of the task to be removed
     */
    public void removeTask(int taskNumber) {
        this.taskList.remove(taskNumber);
    }




}
