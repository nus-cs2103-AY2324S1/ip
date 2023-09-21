package duke.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

public class TaskList {
    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }
    public ArrayList<Task> getTaskList() {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        return this.tasklist;
    }

    /**
     * Marks a Task in the TaskList
     * Returns the Task marked.
     *
     * @param input The number of the Task to be marked.
     * @return The marked Task.
     */
    public Task markTask(int input) throws DukeException {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        if (input < 1 || input > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        task.mark();
        return task;
    }

    /**
     * Unmarks a Task in the TaskList
     * Returns the Task unmarked.
     *
     * @param input The number of the Task to be unmarked.
     * @return The unmarked Task.
     */
    public Task unmarkTask(int input) throws DukeException {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        if (input < 1 || input > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        task.unmark();
        return task;
    }


    /**
     * Deletes a Task from the TaskList.
     * Returns the Task deleted.
     *
     * @param input The number of the Task to be deleted.
     * @return The deleted Task.
     */
    public Task deleteTask(int input) throws DukeException {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        if (input < 1 || input > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        return tasklist.remove(input - 1);
    }

    /**
     * Adds a new ToDo Task to the TaskList.
     * Returns the ToDo Task added.
     *
     * @param description Description of the Task to be added.
     * @return The newly created ToDo Task.
     */
    public Task addToDo(String description) {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        Task newTask = new ToDo(description);
        tasklist.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Deadline Task to the TaskList.
     * Returns the Deadline Task added.
     *
     * @param description Description of the Task to be added.
     * @param deadline The deadline of the Deadline Task.
     * @return The newly created Deadline Task.
     */
    public Task addDeadline(String description, String deadline) {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        Task newTask = new Deadline(description, deadline);
        tasklist.add(newTask);
        return newTask;
    }

    /**
     * Searches the Tasklist for tasks with a specific description.
     *
     * @param description Description of the Tasks to be found.
     * @return A new ArrayList of the Tasks found in the search.
     */
    public ArrayList<Task> searchTasks(String description) {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasklist) {
            if (t.getDescription().contains(description)) {
                result.add(t);
            }
        }
        return result;
    }

    public ArrayList<Task> viewSchedule(String date) {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasklist) {
            if (t instanceof Event) {
                Event event = (Event) t;
                try {
                    LocalDate parsedDate = LocalDate.parse(date);
                    String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    if (isDateMatched(formattedDate, event.getStart()) || isDateMatched(formattedDate,
                            event.getEnd())) {
                        result.add(event);
                    }
                } catch (DateTimeParseException e) {
                    if (isDateMatched(date, event.getStart()) || isDateMatched(date, event.getEnd())) {
                        result.add(event);
                    }
                }
            } else if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                try {
                    LocalDate parsedDate = LocalDate.parse(date);
                    String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    if (isDateMatched(formattedDate, deadline.getDeadline())) {
                        result.add(deadline);
                    }
                } catch (DateTimeParseException e) {
                    if (isDateMatched(date, deadline.getDeadline())) {
                        result.add(deadline);
                    }
                }
            }
        }
        return result;

    }

    public boolean isDateMatched(String actual, String expected) {
        return actual.contains(expected);
    }


    /**
     * Adds a new Event Task to the TaskList.
     * Returns the Event Task added.
     *
     * @param description Description of the Task to be added.
     * @param from The start time of the Event.
     * @param to The end time of the Event.
     * @return The newly created Event Task.
     */
    public Task addEvent(String description, String from, String to) {
        assert this.tasklist.size() >= 0 : "TaskList size is less than 0";
        Task newTask = new Event(description, from, to);
        tasklist.add(newTask);
        return newTask;
    }
}
