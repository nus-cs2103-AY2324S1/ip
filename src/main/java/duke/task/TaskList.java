package duke.task;

import duke.Duke;
import duke.exception.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * duke.task.TaskList class that contains the task list.
 *
 * @author Pearlynn
 */

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for duke.task.TaskList class.
     *
     * @param list The array list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the array list of tasks.
     *
     * @return An array list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the task.
     *
     * @param index The index of the task.
     * @return The task at the index.
     */
    private Task getTask(int index) {
        return getList().get(index);
    }

    /**
     * Adds a task to the array list of tasks.
     *
     * @param task The task to be added.
     */
    private void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the array list of tasks.
     *
     * @param index The index of the task to be deleted.
     */
    private void deleteTask(int index) {
        this.list.remove(index);
    }

    /**
     * Returns the length of the array list of tasks.
     *
     * @return The length of the array list of tasks.
     */
    private int getLength() {
        return this.list.size();
    }

    /**
     * Marks the task as done.
     *
     * @param command The command given by the user.
     */
    public void mark(String command) {
        try {
            String[] result = command.split(" ");
            if (result.length == 1 && !command.startsWith("mark ") && command.length() > 4) {
                throw new NoSpaceAfterException("mark");
            } else if (result.length == 1) {
                throw new EmptyTaskException("mark");
            }
            int idx = Character.getNumericValue(command.charAt(5));
            Task t = getTask(idx - 1);
            t.markAsDone();
            Duke.ui.printMarkTask(t);
        } catch (IndexOutOfBoundsException e) {
            Duke.ui.printIndexOutOfBoundsException(getList());
        } catch (NoSpaceAfterException | EmptyTaskException e) {
            Duke.ui.showError(e.getMessage());
        }
    }

    /**
     * Marks the task as undone.
     *
     * @param command The command given by the user.
     */
    public void unmark(String command) {
        try {
            String[] result = command.split(" ");
            if (result.length == 1 && !command.startsWith("unmark ") && command.length() > 6) {
                throw new NoSpaceAfterException("unmark");
            } else if (result.length == 1) {
                throw new EmptyTaskException("unmark");
            }
            int idx = Character.getNumericValue(command.charAt(7));
            Task t = getTask(idx - 1);
            t.markAsUndone();
            Duke.ui.printUnmarkTask(t);
        } catch (IndexOutOfBoundsException e) {
            Duke.ui.printIndexOutOfBoundsException(getList());
        } catch (NoSpaceAfterException | EmptyTaskException e) {
            Duke.ui.showError(e.getMessage());
        }
    }

    /**
     * Adds a todo to the list of tasks.
     *
     * @param command The command given by the user.
     */
    public void todo(String command) {
        try {
            String[] result = command.split(" ");
            if ((result.length == 1 && !command.startsWith("todo ") && command.length() > 4) ||
                    (result.length > 1 && !command.startsWith("todo "))) {
                throw new NoSpaceAfterException("todo");
            } else if (result.length == 1) {
                throw new EmptyDescriptionException("todo");
            }
            Todo todo = new Todo(command.substring(5));
            addTask(todo);
            Duke.ui.printAddTask(todo, getLength());
        } catch (NoSpaceAfterException | EmptyDescriptionException e) {
            Duke.ui.showError(e.getMessage());
        }
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param command The command given by the user.
     */
    public void deadline(String command) {
        try {
            String[] result1 = command.split(" ");
            String[] result2 = command.split("/by");
            if ((result1.length == 1 && !command.startsWith("deadline ") && command.length() > 8) ||
                    (result1.length > 1 && !command.startsWith("deadline "))) {
                throw new NoSpaceAfterException("deadline");
            } else if (result1.length == 1 ||
                    (command.contains("/by") &&
                            textBtwnWords(command, "deadline", "/by").isBlank())) {
                throw new EmptyDescriptionException("deadline");
            } else if (!command.contains("/by")) {
                throw new DukeException("☹ OOPS!!! Pls provide a date/time for the deadline.");
            } else if (!command.contains(" /by")) {
                throw new NoSpaceBeforeException("/by");
            } else if (result2.length == 1 || result2[1].isBlank()) {
                throw new DukeException("☹ OOPS!!! The date/time for the deadline cannot be empty.");
            } else if (!command.contains("/by ")) {
                throw new NoSpaceAfterException("/by");
            }
            Deadline deadline = new Deadline(command.substring(9, command.indexOf("/") - 1),
                    command.substring(command.indexOf("/by") + 4));
            addTask(deadline);
            Duke.ui.printAddTask(deadline, getLength());
        } catch (DateTimeParseException e) {
            Duke.ui.printDateTimeParseException();
        } catch (NoSpaceAfterException | EmptyDescriptionException | NoSpaceBeforeException | DukeException e) {
            Duke.ui.showError(e.getMessage());
        }
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param command The command given by the user.
     */
    public void event(String command) {
        try {
            String[] result1 = command.split(" ");
            String[] result2 = command.split("/to");
            if ((result1.length == 1 && !command.startsWith("event ") && command.length() > 5) ||
                    (result1.length > 1 && !command.startsWith("event "))) {
                throw new NoSpaceAfterException("event");
            } else if (result1.length == 1 ||
                    (command.contains("/from") &&
                            textBtwnWords(command, "event", "/from").isBlank())) {
                throw new EmptyDescriptionException("event");
            } else if (!command.contains("/from")) {
                throw new DukeException("☹ OOPS!!! Pls provide a start date/time for the event.");
            } else if (!command.contains(" /from")) {
                throw new NoSpaceBeforeException("/from");
            } else if (!command.contains("/to")) {
                throw new DukeException("☹ OOPS!!! Pls provide an end date/time for the event.");
            } else if (command.contains("/from ") && command.contains("/to") &&
                    textBtwnWords(command, "/from", "/to").isBlank()) {
                throw new DukeException("☹ OOPS!!! The start date/time for the event cannot be empty.");
            } else if (!command.contains("/from ")) {
                throw new NoSpaceAfterException("/from");
            } else if (!command.contains(" /to")) {
                throw new NoSpaceBeforeException("/to");
            } else if (result2.length == 1 || result2[1].isBlank()) {
                throw new DukeException("☹ OOPS!!! The end date/time for the event cannot be empty.");
            } else if (!command.contains("/to ")) {
                throw new NoSpaceAfterException("/to");
            }
            Event event = new Event(command.substring(6, command.indexOf("/") - 1),
                    command.substring(command.indexOf("/from") + 6, command.indexOf("/to") - 1),
                    command.substring(command.indexOf("/to") + 4));
            addTask(event);
            Duke.ui.printAddTask(event, getLength());
        } catch (DateTimeParseException e) {
            Duke.ui.printDateTimeParseException();
        } catch (NoSpaceAfterException | EmptyDescriptionException | NoSpaceBeforeException | DukeException e) {
            Duke.ui.showError(e.getMessage());
        }
    }

    /**
     * Deletes the task.
     *
     * @param command The command given by the user.
     */
    public void delete(String command) {
        try {
            String[] result = command.split(" ");
            if (result.length == 1 && !command.startsWith("delete ") && command.length() > 6) {
                throw new NoSpaceAfterException("delete");
            } else if (result.length == 1) {
                throw new EmptyTaskException("delete");
            }
            int idx = Character.getNumericValue(command.charAt(7));
            Task t = getTask(idx - 1);
            deleteTask(idx - 1);
            Duke.ui.printDeleteTask(t, getLength());
        } catch (IndexOutOfBoundsException e) {
            Duke.ui.printIndexOutOfBoundsException(getList());
        } catch (NoSpaceAfterException | EmptyTaskException e) {
            Duke.ui.showError(e.getMessage());
        }
    }

    private String textBtwnWords(String text, String first, String second) {
        return text.substring(text.indexOf(first) + first.length() + 1, text.indexOf(second));
    }
}
