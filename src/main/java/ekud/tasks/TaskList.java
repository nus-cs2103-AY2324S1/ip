package ekud.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ekud.exceptions.EkudIOException;
import ekud.exceptions.EkudIllegalArgException;
import ekud.exceptions.EkudInvalidCommandException;
import ekud.parser.Parser;

/**
 * TaskList handles task modifications and invalid user inputs for
 * modifying tasks, and is a key component of the chatbot.
 */
public class TaskList {
    // Actual list storing the tasks
    private List<Task> tasks;
    // Cached list before clearing the main list
    private List<Task> cachedTasks;

    /**
     * Constructs a TaskList which initialises its arrays.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.cachedTasks = new ArrayList<>();
    }

    /**
     * Returns the tasks as a string.
     *
     * @return String response message for user.
     */
    public String showTasks() {
        if (this.tasks.isEmpty()) {
            return "Your to-do list is currently empty :o";
        }
        StringBuilder output = new StringBuilder("Here is your to-do list:\n");
        int len = this.tasks.size();
        for (int i = 0; i < len; i++) {
            output.append(String.format("%d. %s\n", i + 1, this.tasks.get(i).toString()));
        }
        return output.toString();
    }

    /**
     * Marks a specific task as done and returns a confirmation message.
     *
     * @param index Index number of task supplied by user.
     * @return String response message for user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public String markTaskAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        return "The following task is marked done, sheeesh:\n" + task;
    }

    /**
     * Marks a specific task as done when loading saved tasks from the hard disk
     * on startup.
     *
     * @param index
     */
    public void markDoneOnStart(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a specific task as not done and returns a confirmation messsage.
     *
     * @param index Index number of task supplied by user.
     * @return String response message for user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public String markTaskAsNotDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsNotDone();
        return "The following task is marked as not done yet:\n" + task;
    }

    /**
     * Changes the priority of a specific task.
     *
     * @param priority
     * @param index
     * @return
     */
    public String changePriority(Priority priority, int index) {
        Task task = this.tasks.get(index);
        task.changePriority(priority);
        return String.format(
                "The following task's priority is set to '%s':\n%s",
                task.getPriority(),
                task);
    }

    /**
     * Returns the confirmation message for adding a task.
     *
     * @param task
     * @return String response message for user.
     */
    private String confirmAddedTask(Task task) {
        return String.format(
                "Got it! I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(),
                this.tasks.size());
    }

    /**
     * Adds a particular saved task to this TaskList.
     *
     * @param taskType Type of task to be added.
     * @param description Description of task.
     * @param priority Priority of task.
     * @param taskDetails Additional details of task.
     * @throws EkudIOException If task failed to be added.
     */
    public void addSavedTask(TaskType taskType, String description, Priority priority,
                               String[] taskDetails) throws EkudIOException {
        Parser parser = new Parser(); // For parsing dateTime
        switch (taskType) {
        case TODO:
            this.tasks.add(new ToDo(description, priority));
            break;
        case DEADLINE:
            LocalDateTime dateTime = parser.parseSavedDateTime(taskDetails[3]);
            this.tasks.add(new Deadline(description, dateTime, priority));
            break;
        case EVENT:
            LocalDateTime fromDateTime = parser.parseSavedDateTime(taskDetails[3]);
            LocalDateTime toDateTime = parser.parseSavedDateTime(taskDetails[4]);
            this.tasks.add(new Event(description, fromDateTime, toDateTime, priority));
            break;
        default:
            throw new EkudIOException("Error with parsing saved tasks: Invalid task type");
        }
    }

    /**
     * Adds a to-do task to this TaskList.
     *
     * @param description Description of the to-do task.
     * @return String response message for user.
     */
    public String addToDo(String description) {
        ToDo newToDo = new ToDo(description, Priority.MEDIUM);
        this.tasks.add(newToDo);
        return this.confirmAddedTask(newToDo);
    }

    /**
     * Adds a deadline task to this TaskList.
     *
     * @param description Description of the deadline task.
     * @param dateTime Date and time to complete this task by.
     * @return String response message for user.
     */
    public String addDeadline(String description, LocalDateTime dateTime) {
        Deadline newDeadline = new Deadline(description, dateTime, Priority.MEDIUM);
        this.tasks.add(newDeadline);
        return this.confirmAddedTask(newDeadline);
    }

    /**
     * Adds an event task to this TaskList and returns a confirmation message.
     *
     * @param description Description of the event task.
     * @param dateTimes Date and Time this event starts and ends.
     * @return String response for user.
     */
    public String addEvent(String description, LocalDateTime ... dateTimes) {
        Event newEvent = new Event(description, dateTimes[0], dateTimes[1], Priority.MEDIUM);
        this.tasks.add(newEvent);
        return this.confirmAddedTask(newEvent);
    }

    /**
     * Deletes a task from this TaskList and returns a confirmation message.
     *
     * @param index Index number of task to be deleted as supplied by user.
     * @return String response message for user.
     * @throws EkudInvalidCommandException If taskList is empty.
     */
    public String deleteTask(int index) throws EkudInvalidCommandException {
        if (this.tasks.isEmpty()) {
            throw new EkudInvalidCommandException("You cannot delete from an empty task list :/");
        }
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return String.format(
                "Alright, this task has been removed:\n%s\nNow you have %d task(s) in the list.",
                task.toString(),
                tasks.size());
    }

    /**
     * Finds a list of tasks matching the user's keyword search and returns it as a String.
     *
     * @param keyword
     * @return String response of tasks for user.
     */
    public String findTasks(String keyword) {
        List<String> matchingTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task.toString());
            }
        }
        if (matchingTasks.isEmpty()) {
            return String.format(
                   "No tasks matching the keyword '%s' were found :(",
                    keyword);
        }
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list: :>\n");
        for (String task: matchingTasks) {
            output.append(task + "\n");
        }
        return output.toString();
    }

    /**
     * Helper function used by the storage object to retrieve the number of tasks to store
     * into the hard disk.
     *
     * @return Number of tasks to be saved.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Helper function used by the storage object to format all tasks to be saved.
     *
     * @param i Index number of task to be formatted.
     * @return Task formatted for saving.
     */
    public String getSaveTaskFormat(int i) {
        return this.tasks.get(i).getSaveFormat();
    }

    /**
     * Clears the current tasks in taskList.
     *
     * @return String response for user.
     */
    public String clear() {
        this.cachedTasks = this.tasks;
        this.tasks = new ArrayList<>();
        return "Task list has been reset :o";
    }

    /**
     * Restores tasks cleared by the most recent clear command.
     *
     * @return String response for user.
     */
    public String undoClear() {
        this.tasks.addAll(cachedTasks);
        return "Cleared task list has been restored :o";
    }
}
