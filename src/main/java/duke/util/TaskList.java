package duke.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

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
     * @return the response message to the user.
     */
    public Response addTask(Task task, Ui ui) throws DukeException {
        assert task != null : "Task cannot be null";
        assert ui != null : "Ui cannot be null";
        Task[] duplicateTasks = taskList.stream()
                                        .filter(t -> t.equals(task))
                                        .toArray(Task[]::new);
        if (duplicateTasks.length > 0) {
            StringBuilder errMessage = new StringBuilder();
            errMessage.append("OOPS!!! The following task already exists:\n");
            IntStream.range(0, duplicateTasks.length)
                     .forEach(i -> errMessage.append(
                             String.format("  %s\n", duplicateTasks[i])));
            throw new DukeException(errMessage.toString());
        }

        taskList.add(task);
        return ui.showAddTask(task, taskList.size());
    }

    /**
     * Lists all the tasks in the {@code TaskList}.
     * If the list is empty, an exception is thrown.
     *
     * @return the response message to the user.
     * @throws DukeException if the list is empty
     */
    public Response listTask(Ui ui) throws DukeException {
        assert ui != null : "Ui cannot be null";

        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        }
        String[] tasks = taskList.stream()
                .map(Task::toString)
                .toArray(String[]::new);

        return ui.showListTask(tasks);
    }

    /**
     * Returns all the tasks that are on the given date.
     * If the list is empty, an exception is thrown.
     *
     * @param key  the keyword to determine the type of task
     * @param date the date to be checked
     * @param ui   the ui to show the list of tasks on the given date to the user
     * @return the response message to the user.
     * @throws DukeException if the list is empty, or the keyword is invalid
     *                       or there is no task on the given date
     */
    public Response printDateTask(Keyword key, LocalDate date, Ui ui) throws DukeException {
        assert key != null : "Keyword cannot be null";
        assert date != null : "Date cannot be null";
        assert ui != null : "Ui cannot be null";

        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        }
        if (key.equals(Keyword.DEADLINE) || key.equals(Keyword.EVENT)) {
            return findTaskOnDate(key, date, ui);
        } else {
            throw new DukeException("OOPS!!! This is not a valid command.");
        }
    }

    private Response findTaskOnDate(Keyword key, LocalDate date, Ui ui) throws DukeException {
        String[] tasksOnDate = taskList.stream()
                .filter(task -> task.onDate(key, date))
                .map(Task::toString)
                .toArray(String[]::new);

        if (tasksOnDate.length != 0) {
            return ui.showPrintDateTask(tasksOnDate,
                    date.format(Time.DATE_DISPLAY_FORMATTER));
        } else {
            throw new DukeException(String.format("OOPS!!! There is nothing happening on %s.",
                    date.format(Time.DATE_DISPLAY_FORMATTER)));
        }
    }

    /**
     * Deletes the task at the given index.
     * If the index is invalid, an exception is thrown.
     *
     * @param index the index of the task to be deleted
     * @param ui the ui to show the deleted task message to the user
     * @return the response message to the user.
     * @throws DukeException if the index is invalid
     */
    public Response deleteTask(int index, Ui ui) throws DukeException {
        assert ui != null : "Ui cannot be null";
        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        }

        if (index >= taskList.size() || index < 0) {
            String errMessage = String.format("OOPS!!! There is no task %d to delete",
                        index + 1);
            throw new DukeException(errMessage);
        }
        Task removedTask = taskList.remove(index);
        return ui.showDeleteTask(removedTask, taskList.size());
    }

    /**
     * Marks or unmarks the task at the given index.
     * If the index is invalid, an exception is thrown.
     *
     * @param index the index of the task to be marked or unmarked
     * @param key the keyword to determine whether to mark or unmark the task
     * @param ui the ui to show the marked or unmarked task message to the user
     * @return the response message to the user.
     * @throws DukeException if the index is invalid or the keyword is invalid
     */
    public Response markTask(int index, Keyword key, Ui ui) throws DukeException {
        assert key != null : "Keyword cannot be null";
        assert ui != null : "Ui cannot be null";

        switch (key) {
        case MARK: // fall through
        case UNMARK:
            break;
        default:
            throw new DukeException("OOPS!!! This is not a valid command.");
        }

        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        }

        boolean isMark = key.equals(Keyword.MARK);
        if (index >= taskList.size() || index < 0) {
            String errMessage = String.format("OOPS!!! There is no task %d to %s",
                        index + 1,
                        key.getKeyword());
            throw new DukeException(errMessage);
        }
        return ui.showMarkTask(isMark, taskList.get(index).mark(isMark));
    }

    /**
     * Finds all the tasks that contain the given task keyword.
     *
     * @param taskKey the task keyword to be found
     * @param ui the ui to show the found tasks message to the user
     * @return the response message to the user.
     * @throws DukeException if the list is empty or the task is not found
     */
    public Response findTask(String taskKey, Ui ui) throws DukeException {
        assert ui != null : "Ui cannot be null";

        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        }
        List<String> tasksFound = new ArrayList<>();
        List<String> taskIndexFound = new ArrayList<>();
        IntStream.range(0, taskList.size())
                 .filter(i -> taskList.get(i)
                         .toString()
                         .toLowerCase()
                         .contains(taskKey.toLowerCase()))
                 .forEach(i -> {
                     tasksFound.add(taskList.get(i).toString());
                     taskIndexFound.add(String.valueOf(i + 1));
                 });
        if (tasksFound.isEmpty()) {
            throw new DukeException(String.format("OOPS!!! There is no task with %s.",
                    taskKey));
        }
        return ui.showFindTask(tasksFound.toArray(new String[0]),
                               taskIndexFound.toArray(new String[0]));
    }

    /**
     * Manipulates all the tasks in the {@code TaskList}.
     * As of now, the supported manipulation includes mark, unmark and delete
     * all tasks.
     * If the list is empty or keyword is invalid, an exception is thrown.
     *
     * @param key the keyword to determine the type of manipulation
     * @throws DukeException if the list is empty or the keyword is invalid
     */
    public void manipulateAllTask(Keyword key) throws DukeException {
        assert key != null : "Keyword cannot be null";

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
    }

    /**
     * Returns the file format of the {@code TaskList}.
     *
     * @return the file format of the {@code TaskList}
     * @see Task#fileFormat()
     */
    public String[] saveTaskList() {
        return taskList.stream()
                       .map(Task::fileFormat)
                       .toArray(String[]::new);
    }

    /**
     * Changes the task list to the given list of tasks.
     *
     * @param taskList the new list of tasks
     */
    public void changeTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Sorts the task list according to the given comparator.
     *
     * @param taskComparator the comparator to sort the task list
     */
    public void sortTaskList(Comparator<Task> taskComparator) {
        taskList.sort(taskComparator);
    }
}
