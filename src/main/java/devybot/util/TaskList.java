package devybot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

import devybot.exceptions.EmptyDescriptionException;
import devybot.exceptions.TaskIndexOutOfBoundsException;

import devybot.tasks.DeadlineTask;
import devybot.tasks.EventTask;
import devybot.tasks.Task;
import devybot.tasks.TodoTask;

/**
 * The TaskList class manages a list of tasks and provides methods for adding,
 * deleting, listing, and marking tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        try {
            if (index >= this.taskList.size() || index < 0) {
                throw new TaskIndexOutOfBoundsException(index);
            }
            Task currentTask = this.taskList.get(index);
            taskList.remove(index);

            String outpString = "Noted. I've removed this task:\n  " + currentTask;
            Ui.showMessage(outpString);
        } catch (TaskIndexOutOfBoundsException e) {
            Ui.showMessage(e.getMessage());
        }
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Lists all tasks in the TaskList and display on UI.
     */
    public void listTasks() {
        if (taskList.size() == 0) {
            Ui.showMessage("Currently no tasks available.");
            return;
        }

        String outpString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            outpString += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        Ui.showMessage(outpString);
    }

    /**
     * Adds a TodoTask to the TaskList.
     *
     * @param userInput The user's input containing the TodoTask description.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public void addTodoTask(String userInput) throws EmptyDescriptionException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }

        Task newTask = new TodoTask(description);
        assert newTask != null : "Task should not be null";
        taskList.add(newTask);

        String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                + " tasks in the list.";
        Ui.showMessage(outpString);
    }

    /**
     * Adds a DeadlineTask to the TaskList.
     *
     * @param userInput The user's input containing the DeadlineTask details.
     * @throws EmptyDescriptionException If the description or deadline is empty.
     */
    public void addDeadlineTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /by ");
        String description = parts[0].substring(8).trim();

        if (description.isEmpty() || parts.length < 2) {
            // need to edit to be more specific
            throw new EmptyDescriptionException("deadline");
        }

        String by = parts[1].trim();

        Task newTask;
        try {
            if (by.contains(" ")) {
                // Contains time, parse as LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                newTask = new DeadlineTask(description, dateTime);
            } else {
                // No time, parse as LocalDate
                LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy"));
                newTask = new DeadlineTask(description, date);
            }
            assert newTask != null : "Task should not be null";
            taskList.add(newTask);

            String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
            Ui.showMessage(outpString);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            Ui.showMessage("Invalid date/time format. Please use 'd/M/yyyy' or 'd/M/yyyy HHmm'.");
        }
    }

    /**
     * Adds an EventTask to the TaskList.
     *
     * @param userInput The user's input containing the EventTask details.
     * @throws EmptyDescriptionException If the description or event details are
     *                                   empty.
     */
    public void addEventTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /from | /to ");
        String description = parts[0].substring(5).trim();

        if (description.isEmpty() || parts.length < 3) {
            // need to edit to be more specific
            throw new EmptyDescriptionException("event");
        }

        String from = parts[1].trim();
        String to = parts[2].trim();

        try {
            // Contains time, parse as LocalDateTime
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            Task newTask = new EventTask(description, fromDateTime, toDateTime);

            assert newTask != null : "Task should not be null";
            taskList.add(newTask);

            String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
            Ui.showMessage(outpString);
        } catch (DateTimeParseException e) {
            Ui.showMessage("Invalid date/time format. Please use 'd/M/yyyy HHmm'.");
        }
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @throws TaskIndexOutOfBoundsException If the index is out of bounds.
     */
    public void markTaskAsDone(int index) throws TaskIndexOutOfBoundsException {
        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        assert currentTask != null : "Task should not be null";
        currentTask.markTask();

        String outpString = "Nice! I've marked this task as done:\n  " + currentTask;
        Ui.showMessage(outpString);
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param index The index of the task to mark as undone.
     * @throws TaskIndexOutOfBoundsException If the index is out of bounds.
     */
    public void markTaskAsUndone(int index) throws TaskIndexOutOfBoundsException {
        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        assert currentTask != null : "Task should not be null";
        currentTask.unmarkTask();

        String outpString = "OK, I've marked this task as not done yet:\n  " + currentTask;
        Ui.showMessage(outpString);
    }

    /**
     * Finds tasks containing a specific description and displays them.
     *
     * @param userInput The user's input containing the description to search for.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public void findTasks(String userInput) throws EmptyDescriptionException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("find");
        }

        String outpString = "Here are the matching tasks in your list:\n";

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.isMatching(description)) {
                outpString += currentTask.toString() + "\n";
            }
        }
        Ui.showMessage(outpString);

    }

    /**
     * Searches for tasks containing a specific description and displays them.
     *
     * @param userInput The user's input containing the description to search for.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public void searchTasks(String userInput) throws EmptyDescriptionException {
        String description = userInput.substring(6).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("search");
        }

        String outpString = "Here are the matching tasks in your list:\n";

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.isContaining(description)) {
                outpString += currentTask.toString() + "\n";
            }
        }

        Ui.showMessage(outpString);
    }
}
