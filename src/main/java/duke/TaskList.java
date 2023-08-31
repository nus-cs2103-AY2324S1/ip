package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a list of tasks.
 * @see Task
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Constructs a {@code TaskList} with the given list of tasks.
     *
     * @param tasks the pre-given list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     * @param ui the ui to show the added task message to the user
     */
    public void addTask(Task task, Ui ui) {
        taskList.add(task);
        ui.showAddTask(task, taskList.size());
    }

    /**
     * Lists all the tasks in the {@code TaskList}.
     * If the list is empty, an exception is thrown.
     *
     * @param ui the ui to show the list of tasks to the user
     * @throws DukeException if the list is empty
     */
    public void listTask(Ui ui) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        }
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = taskList.get(i).toString();
        }
        ui.listTask(tasks);
    }

    /**
     * Prints all the tasks that are on the given date.
     * If the list is empty, an exception is thrown.
     *
     * @param key the keyword to determine the type of task
     * @param date the date to be checked
     * @param ui the ui to show the list of tasks on the given date to the user
     * @throws DukeException if the list is empty, or the keyword is invalid
     * or there is no task on the given date
     */
    public void printDateTask(Keyword key, LocalDate date, Ui ui) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        } else if (key.equals(Keyword.DEADLINE) || key.equals(Keyword.EVENT)) {
            List<Task> tasksOnDate = new ArrayList<>();
            for (Task task : taskList) {
                if (task.onDate(key, date)) {
                    tasksOnDate.add(task);
                }
            }
            if (!tasksOnDate.isEmpty()) {
                String[] tasks = new String[tasksOnDate.size()];
                for (int i = 0; i < tasks.length; i++) {
                    tasks[i] = tasksOnDate.get(i).toString();
                }
                ui.printDateTask(tasks, date.format(Time.DATE_DISPLAY_FORMATTER));
            } else {
                throw new DukeException(String.format("OOPS!!! There is nothing happening on %s.",
                        date.format(Time.DATE_DISPLAY_FORMATTER)));
            }
        } else {
            throw new DukeException("OOPS!!! This is not a valid command.");
        }
    }

    /**
     * Deletes the task at the given index.
     * If the index is invalid, an exception is thrown.
     *
     * @param index the index of the task to be deleted
     * @param ui the ui to show the deleted task message to the user
     * @throws DukeException if the index is invalid
     */
    public void deleteTask(int index, Ui ui) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            listTask(ui);
            throw new DukeException(String.format("OOPS!!! There is no task %d to delete", index + 1));
        }
        Task removedTask = taskList.remove(index);
        ui.showDeleteTask(removedTask, taskList.size());
    }

    /**
     * Marks or unmarks the task at the given index.
     * If the index is invalid, an exception is thrown.
     *
     * @param index the index of the task to be marked or unmarked
     * @param key the keyword to determine whether to mark or unmark the task
     * @param ui the ui to show the marked or unmarked task message to the user
     * @throws DukeException if the index is invalid or the keyword is invalid
     */
    public void markTask(int index, Keyword key, Ui ui) throws DukeException {
        switch (key) {
        case MARK:
        case UNMARK:
            break;
        default:
            throw new DukeException("OOPS!!! This is not a valid command.");
        }
        boolean isMark = key.equals(Keyword.MARK);
        if (index >= taskList.size() || index < 0) {
            String err = String.format("OOPS!!! There is no task %d to %s",
                     index + 1, key.getKeyword());
            listTask(ui);
            throw new DukeException(err);
        }
        ui.showMarkTask(isMark, taskList.get(index).mark(isMark));
    }

    /**
     * Manipulates all the tasks in the {@code TaskList}.
     * As of now, the supported manipulation includes mark, unmark and delete
     * all tasks.
     * If the list is empty or keyword is invalid, an exception is thrown.
     *
     * @param key the keyword to determine the type of manipulation
     * @param ui the ui to show the manipulated tasks message to the user
     * @throws DukeException if the list is empty or the keyword is invalid
     */
    public void manipulateAllTask(Keyword key, Ui ui) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(String.format("OOPS!!! There are no tasks to %s.",
                    key.getKeyword()));
        }
        if (key.equals(Keyword.DELETE)) {
            taskList.clear();
        } else if (key.equals(Keyword.MARK) || key.equals(Keyword.UNMARK)) {
            taskList.forEach(t -> t.mark(key.equals(Keyword.MARK)));
        } else {
            throw new DukeException("OOPS!!! This is not a valid command.");
        }
        ui.showManipulateAllTask(key.getKeyword());
    }
}
