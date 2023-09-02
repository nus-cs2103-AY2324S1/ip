package duke;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a list of tasks in the Duke application.
 * This class manages the collection of tasks, as well as their addition, deletion,
 * marking as done/undone, and reading/saving from/to data sources.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList object and initializes it with data from the given list of strings.
     *
     * @param data A list of strings containing task data to be loaded into the task list.
     */
    public TaskList(ArrayList<String> data) {
        this.tasks = new ArrayList<>();
        for (String line : data) {
            readLine(line);
        }
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private Task readLine(String line) {
        String[] split = line.split(" \\| ");
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task = null;
        if (type.equals("T")) {
            task = this.addToDo(description);
        } else if (type.equals("E")) {
            task = this.addEvent(description, split[3], split[4]);
        } else if (type.equals("D")) {
            task = this.addDeadline(description, split[3]);
        }

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    private void checkIndex(int index) throws DukeException {
        if (index < 1 || index - 1 >= tasks.size()) {
            throw new DukeException("duke.task.Task number is out of range.");
        }
    }

    /**
     * Adds a task to the task list and displays a corresponding message using the provided UI.
     *
     * @param task The task to be added.
     * @param ui   The user interface to interact with the user or display messages.
     */
    public void addTask(Task task, Ui ui) {
        this.tasks.add(task);
        ui.addMessage(task, tasks.size());
    }

    /**
     * Deletes a task from the task list by index and displays a corresponding message using the provided UI.
     *
     * @param index The index of the task to be deleted.
     * @param ui    The user interface to interact with the user or display messages.
     * @throws DukeException If the provided index is out of range.
     */
    public void deleteTask(int index, Ui ui) throws DukeException {
        checkIndex(index);
        Task task = this.tasks.remove(index - 1);
        ui.deleteMessage(task, tasks.size());
    }

    /**
     * Finds the corresponding tasks with descriptions matching the keyword.
     *
     * @param keyword The keyword that is being searched in description.
     * @param ui      The user interface to interact with the user or display messages.
     */
    public void findTask(String keyword, Ui ui) {
        ArrayList<String> str = new ArrayList<>();
        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                str.add(task.toString());
            }
        }
        if (str.size() > 0) {
            ui.findMessage();
            for (String task : str) {
                ui.showMessage(task);
            }
        } else {
            ui.findNoMessage();
        }
    }

    /**
     * Marks a task from the task list by index and displays a corresponding message using the provided UI.
     *
     * @param index The index of the task to be deleted.
     * @param ui    The user interface to interact with the user or display messages.
     * @throws DukeException If the provided index is out of range.
     */
    public void markTask(int index, Ui ui) throws DukeException {
        checkIndex(index);
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
        ui.markMessage(task);
    }

    /**
     * Unmarks a task from the task list by index and displays a corresponding message using the provided UI.
     *
     * @param index The index of the task to be deleted.
     * @param ui    The user interface to interact with the user or display messages.
     * @throws DukeException If the provided index is out of range.
     */
    public void unmarkTask(int index, Ui ui) throws DukeException {
        checkIndex(index);
        Task task = this.tasks.get(index - 1);
        task.markAsUndone();
        ui.unmarkMessage(task);
    }

    /**
     * Creates a new ToDo task with the given description, adds it to the task list,
     * and returns the created task.
     *
     * @param description The description of the ToDo task.
     * @return The created ToDo task.
     */
    public ToDo addToDo(String description) {
        ToDo toDo = new ToDo(description);
        this.tasks.add(toDo);
        return toDo;
    }

    /**
     * Creates a new Event task with the given description, start date/time, end date/time,
     * adds it to the task list, and returns the created task.
     *
     * @param description The description of the Event task.
     * @param from        The start date and time of the event (in "yyyy-MM-dd HH:mm" format).
     * @param to          The end date and time of the event (in "yyyy-MM-dd HH:mm" format).
     * @return The created Event task.
     */
    public Event addEvent(String description, String from, String to) {
        Event event = new Event(description,
                                LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        this.tasks.add(event);
        return event;
    }

    /**
     * Creates a new Deadline task with the given description and deadline date/time,
     * adds it to the task list, and returns the created task.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline date and time (in "yyyy-MM-dd HH:mm" format).
     * @return The created Deadline task.
     */
    public Deadline addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, LocalDateTime.parse(by,
                                                                          DateTimeFormatter.ofPattern(
                                                                                  "yyyy-MM-dd HH:mm")));
        this.tasks.add(deadline);
        return deadline;
    }

    /**
     * Saves the task list data to a specified writer.
     *
     * @param writer The writer to use for saving the data.
     * @throws IOException If an error occurs during the writing process.
     */
    public void save(BufferedWriter writer) throws IOException {
        for (Task task : tasks) {
            writer.write(task.toSaveLine());
            writer.newLine();
        }
    }

    /**
     * Generates a string representation of the task list.
     *
     * @return A formatted string containing the tasks in the list.
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += ((i + 1) + "." + tasks.get(i) + "\n");
        }
        return str;
    }

}
