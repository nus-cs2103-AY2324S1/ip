package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents and organises a list of task.
 */
public class TaskList {
    /**
     * Thrown if a function is trying to access a task index out of range.
     */
    public static class TaskIndexOutOfRange extends Exception {
        private TaskIndexOutOfRange() {
            super();
        }
    }

    /**
     * Thrown if a method is trying to update the wrong type of task.
     */
    public static class WrongTaskTypeException extends Exception {
        private final Task.Type expected;
        private final int index;

        private WrongTaskTypeException(Task.Type expected, int index) {
            super();
            this.expected = expected;
            this.index = index;
        }

        private static String typeToString(Task.Type type) {
            switch (type) {
            case TODO:
                return "a todo task";
            case DEADLINE:
                return "a deadline";
            case EVENT:
                return "an event";
            default:
                assert false: "Task type must be indicated when calling this method!";
                return "a task";
            }
        }

        @Override
        public String getMessage() {
            return "task at index " + (this.index + 1)
                    + " is not " + typeToString(this.expected);
        }
    }

    private ArrayList<Task> taskList;

    /**
     * Instantiates the task list with no task.
     * Creates a new empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Instantiates the task list with the given task.
     * @param taskList The task list to start with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the end of the list.
     * @param task The task to add.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks a task with the given index as done.
     * @param index The task index to mark done.
     * @return The task that has been marked done.
     * @throws TaskIndexOutOfRange If the task index given is out of range.
     */
    public Task markTaskAsDone(int index) throws TaskIndexOutOfRange {
        try {
            this.taskList.get(index).markAsDone();
            assert this.taskList.get(index).isDone(): "Task must be marked done";
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Marks a task with the given index as not done.
     * @param index The task index to mark as not done.
     * @return The task that has been marked not done.
     * @throws TaskIndexOutOfRange If the task index given is out of range.
     */
    public Task markTaskAsNotDone(int index) throws TaskIndexOutOfRange {
        try {
            this.taskList.get(index).markAsNotDone();
            assert !this.taskList.get(index).isDone(): "Task must be marked not done";
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Deletes a task with the given index.
     * @param index The task index to delete.
     * @return The task that has been deleted.
     * @throws TaskIndexOutOfRange If the task index given is out of range.
     */
    public Task deleteTask(int index) throws TaskIndexOutOfRange {
        try {
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Displays the list of task with the given filters.
     * @param taskList The task list to display.
     * @param isExcludingDone Whether to exclude tasks already done.
     * @param date The date to include deadlines before and events happening on,
     *             null if to not filter by date.
     */
    private static String getTasks(ArrayList<Task> taskList, boolean isExcludingDone, LocalDate date) {
        if (isExcludingDone) {
            taskList.removeIf(Task::isDone);
        }
        if (date != null) {
            taskList.removeIf(task -> !task.containsDate(date));
        }
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            data.append((i + 1) + ". " + taskList.get(i) + (i == taskList.size() - 1 ? "" : "\n"));
        }
        return data.toString();
    }

    /**
     * Displays tasks with the given filters.
     * Assume that there is no filtering by task type (todo/deadline/event).
     * @param isExcludingDone Whether to exclude tasks already done.
     * @param date The date to filter in deadlines before and events happening on.
     * @return The string representation of the filtered list of tasks.
     */
    public String getTasks(boolean isExcludingDone, LocalDate date, Task.Type type) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        switch (type) {
        case TODO:
            taskList.removeIf(task -> !(task instanceof ToDo));
            break;
        case DEADLINE:
            taskList.removeIf(task -> !(task instanceof Deadline));
            break;
        case EVENT:
            taskList.removeIf(task -> !(task instanceof Event));
            break;
        default:
            break;
        }
        return TaskList.getTasks(taskList, isExcludingDone, date);
    }

    /**
     * Returns the number of tasks in this task list.
     * @return Number of tasks in this task list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Updates the name of the task of the given index with the new name.
     * @param index The index of the task in the list.
     * @param newName The new name.
     * @return The task that has been updated.
     */
    public Task updateName(int index, String newName) throws TaskIndexOutOfRange {
        try {
            Task task = this.taskList.get(index);
            task.updateName(newName);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Update the deadline at the specified index.
     * @param index The index of the deadline in the task list.
     * @param newDeadlineTime The new deadline.
     * @return The task that has been updated.
     * @throws TaskIndexOutOfRange If the index given is invalid.
     * @throws WrongTaskTypeException If the task with the given index is not a deadline.
     */
    public Task updateDeadline(int index, LocalDateTime newDeadlineTime)
            throws TaskIndexOutOfRange, WrongTaskTypeException {
        try {
            Task task = this.taskList.get(index);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                deadline.updateDeadlineTime(newDeadlineTime);
                return task;
            } else {
                throw new WrongTaskTypeException(Task.Type.DEADLINE, index);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Update the start time of an event.
     * @param index The index of the event in this task list.
     * @param newStartTime The new start time of the event.
     * @return The task that has been updated.
     * @throws TaskIndexOutOfRange If the index given is invalid
     * @throws WrongTaskTypeException If the task with the given index is not of type event.
     */
    public Task updateStartTime(int index, LocalDateTime newStartTime)
            throws TaskIndexOutOfRange, WrongTaskTypeException {
        try {
            Task task = this.taskList.get(index);
            if (task instanceof Event) {
                Event event = (Event) task;
                event.updateStartTime(newStartTime);
                return task;
            } else {
                throw new WrongTaskTypeException(Task.Type.EVENT, index);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Update the end time of the task with the given index.
     * @param index The index of the event in this task list.
     * @param newEndTime The new end time of the event.
     * @return The task that has been updated.
     * @throws TaskIndexOutOfRange If the index given is invalid.
     * @throws WrongTaskTypeException If the task at the given index is not an event.
     */
    public Task updateEndTime(int index, LocalDateTime newEndTime)
            throws TaskIndexOutOfRange, WrongTaskTypeException {
        try {
            Task task = this.taskList.get(index);
            if (task instanceof Event) {
                Event event = (Event) task;
                event.updateEndTime(newEndTime);
                return task;
            } else {
                throw new WrongTaskTypeException(Task.Type.EVENT, index);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Save data to a given storage,
     * by first converting this list of task to storage-readable form.
     * @param storage The storage to save data to.
     * @throws Storage.FileIoException If there is an IO error.
     */
    public void saveData(Storage storage) throws Storage.FileIoException {
        StringBuilder data = new StringBuilder();
        for (Task task: this.taskList) {
            data.append(task.getData()).append("\n");
        }
        storage.saveData(data.toString());
    }

    /**
     * Display the tasks that match the given input.
     * @param input The search parameter.
     */
    public String results(String input, Ui userInterface) {
        ArrayList<Task> list = (ArrayList<Task>) this.taskList.clone();
        list.removeIf(task -> !task.containsString(input));
        return TaskList.getTasks(list, false, null);
    }

    /**
     * Determines whether the given task is in this task list.
     * @param task The task to check
     * @return Whether the task is in this task list
     */
    public boolean contains(Task task) {
        return this.taskList.contains(task);
    }
}
