package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * Represents the list of tasks that the chatbot keeps track of.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();
    private Hashtable<String, ArrayList<Task>> deadlinesDateTable = new Hashtable<>();
    private Hashtable<String, ArrayList<Task>> eventsStartDateTable = new Hashtable<>();
    private Hashtable<String, ArrayList<Task>> eventsEndDateTable = new Hashtable<>();
    private int size = 0;

    /**
     * Default constructor for an empty task list.
     */
    public TaskList() {}

    /**
     * Constructor for a task list loaded from the data file.
     * @param list The tasks list loaded from the data file
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.size = list.size();
        for (Task task : list) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                addDeadlineDateToTable(deadline);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                addEventDateToTable(event);
            }
        }
    }

    /**
     * Returns the size of the list.
     * @return The size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns whether the list is empty.
     * @return Whether the list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the string of deadline tasks due by a certain date.
     * @param date The date to search for
     * @return The string of deadline tasks
     */
    public String getDeadlineDateTasks(String date) {
        String output = "";
        if (deadlinesDateTable.containsKey(date)) {
            output = taskListToString(deadlinesDateTable.get(date));
        }
        if (output.isBlank()) {
            return "No tasks on this date";
        } else {
            return output;
        }
    }

    /**
     * Returns the string of event tasks starting on a certain date.
     * @param date The date to search for
     * @return The string of event tasks
     */
    public String getEventStartDateTasks(String date) {
        String output = "";
        if (eventsStartDateTable.containsKey(date)) {
            output = taskListToString(eventsStartDateTable.get(date));
        }

        if (output.isBlank()) {
            return "No tasks on this date";
        } else {
            return output;
        }
    }

    /**
     * Returns the string of event tasks ending on a certain date.
     * @param date The date to search for
     * @return The string of event tasks
     */
    public String getEventEndDateTasks(String date) {
        String output = "";
        if (eventsEndDateTable.containsKey(date)) {
            output = taskListToString(eventsEndDateTable.get(date));
        }
        if (output.isBlank()) {
            return "No tasks on this date";
        } else {
            return output;
        }
    }

    /**
     * Returns the string of tasks with a certain description.
     * @param description The description to search for
     * @return The string of tasks
     */
    public String findTasks(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("!!!: Please provide a description to search for");
        }
        ArrayList<Task> taskList = list.stream()
                .filter(task -> task.description.contains(description))
                .collect(Collectors.toCollection(ArrayList::new));
        if (taskList.isEmpty()) {
            return "No tasks with this description";
        } else {
            return taskListToString(taskList);
        }
    }

    /**
     * Marks a task in the list as done.
     * @param options The index of the task to be marked as done
     * @return The task marked as done
     * @throws DukeException if index is not a number or index is out of bounds
     */
    public Task markDone(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch (NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be marked done");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, "
                    + "there are currently %d items in the list", size));
        }
        Task task = list.get(index);
        task.markDone();
        return task;
    }

    /**
     * Marks a task in the list as not done.
     * @param options The index of the task to be marked as not done
     * @return The task marked as not done
     * @throws DukeException if index is not a nubmer or index is out of bounds
     */
    public Task unmarkDone(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch (NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be marked not done");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, "
                    + "there are currently %d items in the list", size));
        }
        Task task = list.get(index);
        task.unmarkDone();
        return task;
    }

    /**
     * Adds a Todo task to the list.
     * @param description The description of the Todo task
     * @return The Todo task
     * @throws DukeException if description is empty
     */
    public Task addTodoToList(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("!!!: Please provide a task description");
        }
        Task task = new Todo(description);
        list.add(task);
        size++;
        return task;
    }

    /**
     * Adds a Deadline task to the list.
     * @param line The string containing the description and due date of the task
     * @return The Deadline task
     * @throws DukeException if description or dueDate is empty or date is not in the correct format
     */
    public Task addDeadlineToList(String line) throws DukeException {
        if (line.indexOf("/by") == 0) {
            throw new DukeException("!!!: Please provide a task description");
        }
        if (!line.contains(" /by ")) {
            throw new DukeException("!!!: Please provide a by date using format \"/by YYYY-MM-DD\"");
        }
        String description = line.substring(0, line.indexOf("/by") - 1);
        if (description.isBlank()) {
            throw new DukeException("!!!: Please provide a task description");
        }
        String dueDate = line.substring(line.indexOf("/by") + 4);
        if (dueDate.isBlank()) {
            throw new DukeException("!!!: Please provide a due date");
        }
        Deadline task;
        try {
            task = new Deadline(description, dueDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not in the correct format YYYY-MM-DD");
        }
        addDeadlineDateToTable(task);
        list.add(task);
        size++;
        return task;
    }

    /**
     * Adds an Event task to the list.
     * @param line The string containing the description, dateFrom and dateTo of the task
     * @return The Event task
     * @throws DukeException if description, dateFrom or dateTo is empty or dates are not in the correct format
     */
    public Task addEventToList(String line) throws DukeException {
        if (line.indexOf("/from") == 0) {
            throw new DukeException("!!!: Please provide a task description");
        }
        if (!line.contains(" /from ")) {
            throw new DukeException("!!!: Please provide a from date using \"/from from_date\"");
        }
        if (!line.contains(" /to ")) {
            throw new DukeException("!!!: Please provide a to date using \"/to to_date\"");
        }
        String description = line.substring(0, line.indexOf("/from") - 1);
        if (description.isBlank()) {
            throw new DukeException("!!!: Please provide a task description");
        }
        String dateFrom = line.substring(line.indexOf("/from") + 6, line.indexOf("/to") - 1);
        if (dateFrom.isBlank()) {
            throw new DukeException("!!!: Please provide a from date");
        }
        String dateTo = line.substring(line.indexOf("/to") + 4);
        if (dateTo.isBlank()) {
            throw new DukeException("!!!: Please provide a to date");
        }
        Event task;
        try {
            task = new Event(description, dateFrom, dateTo);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not in the correct format YYYY-MM-DD");
        }
        addEventDateToTable(task);
        list.add(task);
        size++;
        return task;
    }

    private void addDeadlineDateToTable(Deadline deadline) {
        String dueDate = deadline.dueDate.toString();
        if (!deadlinesDateTable.containsKey(dueDate)) {
            deadlinesDateTable.put(dueDate, new ArrayList<>());
        }
        deadlinesDateTable.get(dueDate).add(deadline);
    }

    private void addEventDateToTable(Event event) {
        String dateFrom = event.dateFrom.toString();
        String dateTo = event.dateTo.toString();
        if (!eventsStartDateTable.containsKey(dateFrom)) {
            eventsStartDateTable.put(dateFrom, new ArrayList<>());
        }
        if (!eventsEndDateTable.containsKey(dateTo)) {
            eventsEndDateTable.put(dateTo, new ArrayList<>());
        }
        eventsStartDateTable.get(dateFrom).add(event);
        eventsEndDateTable.get(dateTo).add(event);
    }

    /**
     * Removes the specified task from the list.
     * @param options The index of the task to be removed
     * @return The task to be removed
     * @throws DukeException if index is not a number or index is out of bounds
     */
    public Task deleteFromList(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch (NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be deleted");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, "
                    + "there are currently %d items in the list", size));
        }
        Task task = list.get(index);
        list.remove(index);
        size--;
        return task;
    }

    /**
     * Returns the string representation of the tasks list to be saved into the data file.
     * @return The string representation of the tasks list
     */
    public String toLogString() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= list.size(); ++i) {
            output.append(list.get(i - 1).toLogString()).append("\n");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return taskListToString(list);
    }

    private String taskListToString(ArrayList<Task> taskList) {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= taskList.size(); ++i) {
            output.append(String.format("%d. %s\n", i, taskList.get(i - 1)));
        }
        return output.toString();
    }
}
