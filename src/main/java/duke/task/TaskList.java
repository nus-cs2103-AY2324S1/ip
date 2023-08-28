package duke.task;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a list of tasks.
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
     * Constructs a TaskList with the given list of tasks.
     *
     * @param taskList An ArrayList of tasks to initialise the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new Todo task to the TaskList.
     *
     * @param parsedInput An ArrayList containing the parsed user input.
     * @throws DukeMissingArgumentException If the user input is missing task description.
     */
    public void newTodo(ArrayList<String> parsedInput) throws DukeMissingArgumentException {
        try {
            Task newTask = new Todo(parsedInput.get(1));
            this.taskList.add(newTask);
            Ui.showTodoMessage(newTask, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    /**
     * Adds a new Deadline task to the TaskList.
     *
     * @param parsedInput An ArrayList containing the parsed user input.
     * @throws DukeMissingArgumentException If the user input is missing task description or /by date.
     * @throws DukeInvalidDateFormatException If the date format in the user input is invalid.
     */
    public void newDeadline(ArrayList<String> parsedInput) throws DukeMissingArgumentException,
            DukeInvalidDateFormatException {
        try {
            Task newTask = new Deadline(parsedInput.get(1), LocalDate.parse(parsedInput.get(2)));
            this.taskList.add(newTask);
            Ui.showDeadlineMessage(newTask, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    /**
     * Adds a new Event task to the TaskList.
     *
     * @param parsedInput An ArrayList containing the parsed user input.
     * @throws DukeMissingArgumentException If the user input is missing task description or /from date or /to date.
     * @throws DukeInvalidDateFormatException If the date format in the user input is invalid.
     * @throws DukeEndDateBeforeStartDateException If the end date is before the start date.
     */
    public void newEvent(ArrayList<String> parsedInput) throws DukeMissingArgumentException,
            DukeInvalidDateFormatException, DukeEndDateBeforeStartDateException {
        try {
            Task newTask = new Event(parsedInput.get(1), LocalDate.parse(parsedInput.get(2)),
                    LocalDate.parse(parsedInput.get(3)));
            this.taskList.add(newTask);
            Ui.showEventMessage(newTask, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    /**
     * Lists all the tasks in the TaskList.
     */
    public void list() {
        Ui.showListMessage(this.taskList);
    }

    /**
     * Marks a task done.
     *
     * @param parsedInput An ArrayList containing the parsed user input.
     * @throws DukeNoTaskFoundException If the specified task index is out of range.
     * @throws DukeInvalidArgumentException If the input for task index is not an integer.
     * @throws DukeMissingArgumentException If the input is missing the task index.
     */
    public void markAsDone(ArrayList<String> parsedInput) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(parsedInput.get(1));
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            Task task = this.taskList.get(i - 1);
            task.markAsDone();
            Ui.showMarkMessage(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    /**
     * Marks a task undone.
     *
     * @param parsedInput An ArrayList containing the parsed user input.
     * @throws DukeNoTaskFoundException If the specified task index is out of range.
     * @throws DukeInvalidArgumentException If the input for task index is not an integer.
     * @throws DukeMissingArgumentException If the input is missing the task index.
     */
    public void markAsUndone(ArrayList<String> parsedInput) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(parsedInput.get(1));
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            Task task = this.taskList.get(i - 1);
            task.markAsUndone();
            Ui.showUnmarkMessage(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    /**
     * Deletes a task.
     *
     * @param parsedInput An ArrayList containing the parsed user input.
     * @throws DukeNoTaskFoundException If the specified task index is out of range.
     * @throws DukeInvalidArgumentException If the input for task index is not an integer.
     * @throws DukeMissingArgumentException If the input is missing the task index.
     */
    public void delete(ArrayList<String> parsedInput) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(parsedInput.get(1));
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            Task removedTask = this.taskList.get(i - 1);
            this.taskList.remove(i - 1);
            Ui.showDeleteMessage(removedTask, this.taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    /**
     * Converts the TaskList into an ArrayList of Strings for storage.
     *
     * @return An ArrayList of Strings representing the tasks in the TaskList.
     */
    public ArrayList<String> stringify() {
        ArrayList<String> stringList = new ArrayList<>();
        for (Task task : this.taskList) {
            stringList.add(task.toString());
        }
        return stringList;
    }
}
