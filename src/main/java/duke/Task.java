package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a task in the Duke chatbot application.
 */
public class Task {
    private String task;
    private TaskStatus status;

    Boolean isNotSaved;
    private static ArrayList<Task> arr = new ArrayList<>();
    private static int counter = 0;

    /**
     * Constructs a Task object with a task description and save status.
     *
     * @param task       The description of the task.
     * @param isNotSaved A boolean indicating whether the task is saved.
     */
    public Task(String task, Boolean isNotSaved) {
        this.task = task;
        this.status = TaskStatus.NOT_DONE;
        this.isNotSaved = isNotSaved;

        if (!task.isEmpty()) {
            addTask(this.task);
        }
    }

    /**
     * Empty constructor for Task.
     */
    public Task() {

    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task's status and description.
     */
    @Override
    public String toString() {
        return status.toString() + " " + this.task;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task description to add.
     */
    public void addTask(String task) {
        if (!task.equals("")) {
            if (!task.isEmpty()) {
                Duke.taskList.addTask(this);
                counter++;
            }
        }
    }

    /**
     * Gets the count of tasks.
     *
     * @return The count of tasks.
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public TaskStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the task.
     *
     * @param taskStatus The status to set.
     */
    public void setStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Parses a date and time string to a LocalDateTime object.
     *
     * @param dateTimeString The date and time string in the format "dd/MM/yyyy HHmm".
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws DukeException If the date and time string has an invalid format.
     */
    public LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        // Split the input string into date and time parts
        String[] parts = dateTimeString.split(" ", 2);

        // Check if there are exactly two parts (date and time)
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid date/time format: "
                    + dateTimeString);
        }

        String datePart = parts[0];
        String timePart = parts[1];

        // Define a formatter for the date part, e.g., "dd/MM/yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Parse the date part into a LocalDate object
        LocalDate date = LocalDate.parse(datePart, dateFormatter);

        // Define a formatter for the time part, e.g., "HHmm"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        // Parse the time part into a LocalTime object
        LocalTime time = LocalTime.parse(timePart, timeFormatter);

        // Combine the date and time into a LocalDateTime object
        return LocalDateTime.of(date, time);
    }

    /**
     * Formats the list of tasks into a human-readable string.
     *
     * @return A formatted string containing the tasks in the list.
     */
    public String formatList() {
        StringBuilder formattedList = new StringBuilder();
        formattedList.append(Ui.horizontalLine).append("Here are the tasks in your list:\n");

        for (int i = 0; i < Duke.taskList.size(); i++) {
            Task task = Duke.taskList.getTasks().get(i);
            formattedList.append((i + 1)).append(". ").append(task.toString()).append("\n");
        }

        formattedList.append(Ui.horizontalLine);
        return formattedList.toString();
    }



    /**
     * Gets the type of the task.
     *
     * @return The string representing task type.
     */
    public String getTaskType() {
        // Your logic to determine the task type based on the instance's actual class
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        } else {
            return ""; // Handle unknown task types or add appropriate logic
        }
    }

    /**
     * Marks a task as done in the task list and updates
     * the storage file accordingly.
     *
     * @param i The index of the task to be marked as done.
     * @return A message confirming the task's status change.
     * @throws DukeException If the provided index is invalid.
     */
    public String mark(int i) throws DukeException {
        if (i > Duke.taskList.getTasks().size() || i <= 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid number :(\n"
                    + Ui.horizontalLine);
        }
        Task markTask = Duke.taskList.getTasks().get(i - 1);
        markTask.status = TaskStatus.DONE;

        // Update the task description in the file
        Storage.updateLineInFile(i, markTask.generateStr());

        return (Ui.horizontalLine + "Nice! I've marked this task as done:\n"
                + markTask.toString() + "\n" + Ui.horizontalLine);
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Checks if the current task contains the specified keyword.
     *
     * @param keyword The keyword to search for within the task.
     * @return {@code true} if the task contains the keyword,
     * {@code false} otherwise.
     */
    public boolean contains(String keyword) {
        return task.contains(keyword);
    }

    /**
     * Unmarks a task as done by changing its status to "not done."
     *
     * @param i The index of the task in the list to unmark.
     * @return A string confirming the task's status change.
     * @throws DukeException If the provided index is invalid.
     */
    public String unmark(int i) throws DukeException {
        if (i > Duke.taskList.getTasks().size() || i <= 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid number :(\n" + Ui.horizontalLine);
        }
        Task unmarkTask = Duke.taskList.getTasks().get(i - 1);
        unmarkTask.status = TaskStatus.NOT_DONE;
        Storage.updateLineInFile(i, unmarkTask.generateStr());
        return (Ui.horizontalLine
                + "Ok, I've marked this task as not done yet:\n"
                + unmarkTask.toString() + "\n" + Ui.horizontalLine);
    }

    /**
     * Deletes a task from the task list and updates the
     * storage file accordingly.
     *
     * @param i The index of the task to be deleted.
     * @return A message confirming the deletion of the task and
     * the current task count.
     * @throws DukeException If the provided index is invalid.
     */
    public String delete(int i) throws DukeException {
        if (i > Duke.taskList.size() || i <= 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid number :(\n" + Ui.horizontalLine);
        }
        Task deleteTask = Duke.taskList.getTasks().get(i - 1);
        counter = counter - 1;
        Duke.taskList.deleteTask(i - 1);
        Storage.deleteLineFromFile(i);
        return (Ui.horizontalLine
                + "Noted. I've removed this task:\n" + deleteTask.toString()
                + "\n" + String.format("Now you have %d tasks in the list\n", counter)
                + Ui.horizontalLine);
    }

    /**
     * Generates String representation to be saved in text file.
     */
    public String generateStr() {
        return task;
    }

    /**
     * Saves the task to a file.
     */
    public void saveToFile() {
        return;
    }
}