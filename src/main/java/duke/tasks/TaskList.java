package duke.tasks;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeIOException;
import duke.exceptions.DukeIllegalArgumentException;
import duke.storage.Storage;

/**
 * A list of tasks.
 */
public class TaskList {

    // Error messages
    private static final String ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS =
            "The task number is out of range. Use \"list\" to see your tasks.";
    private static final String ERROR_MESSAGE_TEMPLATE_EMPTY_DESCRIPTION =
            "The description of a %s task cannot be blank.";
    private static final String ERROR_MESSAGE_TEMPLATE_VOWEL_EMPTY_DESCRIPTION =
            "The description of an %s task cannot be blank.";
    private static final String ERROR_MESSAGE_EMPTY_DEADLINE =
            "The date/time of a Deadline task cannot be blank.";
    private static final String ERROR_MESSAGE_TEMPLATE_EMPTY_EVENT_DATE_TIME =
            "The %s date/time of an Event task cannot be blank.";

    private static final String ERROR_MESSAGE_ADD_TODO_WRONG_TASK_TYPE =
            "Only ToDo tasks can be added with just a description.";
    private static final String ERROR_MESSAGE_ADD_DEADLINE_WRONG_TASK_TYPE =
            "Only Deadline tasks can be added with a description and deadline.";
    private static final String ERROR_MESSAGE_ADD_EVENT_WRONG_TASK_TYPE =
            "Only Event tasks can be added with a description, start and end date/time.";

    // Message Templates
    private static final String MESSAGE_ADD_TASK_TEMPLATE =
            "Got it. I've added this task:%n%s%nNow you have %d %s in the list.";
    private static final String MESSAGE_DELETE_TASK_TEMPLATE =
            "Noted. I've removed this task:%n%s%nNow you have %d %s in the list.";
    private static final String MESSAGE_MARK_TASK_TEMPLATE = "Nice! I've marked this task as done:%n%s";
    private static final String MESSAGE_UNMARK_TASK_TEMPLATE = "OK, I've marked this task as not done yet:%n%s";
    private static final String MESSAGE_TEMPLATE_FOUND_TASKS = "Here %s the %d matching %s in your list:%s";
    private static final String MESSAGE_TEMPLATE_NO_FOUND_TASKS = "There are no matching tasks in your list.";
    private static final String MESSAGE_LIST_TASKS_HEADER = "Here are the tasks in your list:";
    private static final String MESSAGE_TEMPLATE_EMPTY_TASKLIST = "%nYou have no tasks in your list.";
    private static final String TASK_DISPLAY_FORMAT_TEMPLATE = "%n%d.%s";

    // An ArrayList that stores the list of tasks.
    protected final ArrayList<Task> tasks;

    /**
     * Enum for types of tasks.
     * Available types: TODO, DEADLINE, EVENT
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    // Storage to save and load tasks from disk.
    private final Storage taskStorage;

    /**
     * Constructor for a TaskList.
     *
     * @param saveFileName The name of the file to save the data to. Eg: tasks.txt
     */
    public TaskList(String saveFileName) {
        this.taskStorage = new Storage(saveFileName);
        this.tasks = new ArrayList<>();
        this.importData();
    }

    // Returns "task" if there is only 1 task in the list, else returns "tasks".
    private String formatTaskPlurality() {
        return this.tasks.size() == 1 ? "task" : "tasks";
    }

    /**
     * Adds a task to the TaskList. This method is private.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @param by The deadline of the task to add.
     * @param start The start date/time of the task to add.
     * @param end The end date/time of the task to add.
     * @return String message
     */
    private String add(TaskType taskType, String description, String by, String start, String end)
            throws DukeIllegalArgumentException {

        // Avoids early return statements in switch statement.
        String output = "";

        switch (taskType) {
        case TODO:
            Task toDoTask = new ToDo(description);
            this.tasks.add(toDoTask);
            this.exportData();
            output = String.format(MESSAGE_ADD_TASK_TEMPLATE,
                    toDoTask, this.tasks.size(), formatTaskPlurality());
            break;
        case DEADLINE:
            Task deadlineTask = new Deadline(description, by);
            this.tasks.add(deadlineTask);
            this.exportData();
            output = String.format(MESSAGE_ADD_TASK_TEMPLATE,
                    deadlineTask, this.tasks.size(), formatTaskPlurality());
            break;
        case EVENT:
            Task eventTask = new Event(description, start, end);
            this.tasks.add(eventTask);
            this.exportData();
            output = String.format(MESSAGE_ADD_TASK_TEMPLATE,
                    eventTask, this.tasks.size(), formatTaskPlurality());
            break;
        default:
            break;
        }
        return output;
    }

    /**
     * Adds a task with description to the TaskList. Used for ToDo tasks.
     *
     * @param taskType The type of task to add. Must be TODO.
     * @param description The description of the task to add.
     * @return String message
     */
    public String add(TaskType taskType, String description) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_EMPTY_DESCRIPTION, "ToDo"));
        }
        if (taskType != TaskType.TODO) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_ADD_TODO_WRONG_TASK_TYPE);
        }
        return this.add(taskType, description, "", "", "");
    }

    /**
     * Adds a task with description and deadline to the TaskList. Used for Deadline tasks.
     *
     * @param taskType The type of task to add. Must be DEADLINE.
     * @param description The description of the task to add.
     * @param by The deadline of the task to add.
     * @return String message
     */
    public String add(TaskType taskType, String description, String by) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_EMPTY_DESCRIPTION, "Deadline"));
        }
        if (by.isBlank()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_EMPTY_DEADLINE);
        }
        if (taskType != TaskType.DEADLINE) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_ADD_DEADLINE_WRONG_TASK_TYPE);
        }
        return this.add(taskType, description, by, "", "");
    }

    /**
     * Adds a task with description, start and end date/time to the TaskList. Used for Event tasks.
     *
     * @param taskType The type of task to add. Must be EVENT.
     * @param description The description of the task to add.
     * @param start The start date/time of the task to add.
     * @param end The end date/time of the task to add.
     * @return String message
     */
    public String add(TaskType taskType, String description, String start, String end)
            throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_VOWEL_EMPTY_DESCRIPTION, "Event"));
        }
        if (start.isBlank()) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_EMPTY_EVENT_DATE_TIME, "start"));
        }
        if (end.isBlank()) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_EMPTY_EVENT_DATE_TIME, "end"));
        }
        if (taskType != TaskType.EVENT) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_ADD_EVENT_WRONG_TASK_TYPE);
        }
        return this.add(taskType, description, "", start, end);
    }

    /**
     * Marks a task as done.
     *
     * @param num The number of the task to be marked as done.
     * @return String message
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    public String mark(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        this.tasks.get(index).markAsDone();
        this.exportData();
        return String.format(MESSAGE_MARK_TASK_TEMPLATE, this.tasks.get(index).toString());
    }

    /**
     * Marks a task as undone.
     *
     * @param num The number of the task to be marked as undone.
     * @return String message
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    public String unmark(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        this.tasks.get(index).unmarkAsDone();
        this.exportData();
        return String.format(MESSAGE_UNMARK_TASK_TEMPLATE, this.tasks.get(index).toString());
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param num The number of the task to be deleted.
     * @return String message
     */
    public String delete(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        Task task = tasks.remove(index);
        this.exportData();
        return String.format(MESSAGE_DELETE_TASK_TEMPLATE, task, this.tasks.size(), formatTaskPlurality());
    }

    /**
     * Prints tasks that contain the keyword in the description.
     *
     * @param keyword The keyword to search for.
     * @return The String representation of the tasks that contain the keyword in the description.
     */
    public String find(String keyword) {
        List<String> foundTasks = tasks.stream()
                .filter(task -> task.hasKeywordInDescription(keyword))
                .map(task -> String.format(TASK_DISPLAY_FORMAT_TEMPLATE, tasks.indexOf(task) + 1, task))
                .collect(toList());

        if (foundTasks.isEmpty()) {
            return MESSAGE_TEMPLATE_NO_FOUND_TASKS;
        } else {
            String isOrAre = foundTasks.size() == 1 ? "is" : "are";
            String taskOrTasks = foundTasks.size() == 1 ? "task" : "tasks";
            return String.format(MESSAGE_TEMPLATE_FOUND_TASKS,
                    isOrAre, foundTasks.size(), taskOrTasks, String.join("", foundTasks));
        }
    }

    /**
     * Returns the String representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_LIST_TASKS_HEADER);
        if (tasks.isEmpty()) {
            sb.append(String.format(MESSAGE_TEMPLATE_EMPTY_TASKLIST));
        } else {
            tasks.stream()
                    .map(task -> String.format(TASK_DISPLAY_FORMAT_TEMPLATE, tasks.indexOf(task) + 1, task))
                    .forEach(sb::append);
        }
        return sb.toString();
    }

    /**
     * Exports the TaskList's contents to the save file.
     * Each task should have the format:
     * TASK_TYPE || IS_DONE || DESCRIPTION || BY_DATE_TIME || START_DATE_TIME || END_DATE_TIME
     *
     * @throws DukeIOException If there is an error saving the tasks to the file.
     */
    private void exportData() throws DukeIOException {
        StringBuilder sb = new StringBuilder();
        tasks.stream()
                .map(Task::export)
                // \n is used here instead of %n to preserve save file formatting
                .forEach(task -> sb.append(task).append("\n"));
        taskStorage.save(sb.toString());
    }

    /**
     * Reads the exported data from the save file and restores the state of the Task List.
     *
     * @throws DukeIOException If there is an error reading from the file.
     */
    private void importData() throws DukeIOException {
        String exportedData = taskStorage.load();
        if (exportedData.isBlank()) {
            return;
        }
        String[] exportedTasks = exportedData.split("\n");
        for (String t : exportedTasks) {
            String[] args = t.split(" \\|\\| ");
            TaskType taskType = TaskType.valueOf(args[0]);
            switch (taskType) {
            case TODO:
                this.add(taskType, args[2]);
                break;
            case DEADLINE:
                this.add(taskType, args[2], args[3]);
                break;
            case EVENT:
                this.add(taskType, args[2], args[4], args[5]);
                break;
            default:
                break;
            }
            if (args[1].equals("X")) {
                this.mark(this.tasks.size());
            }
        }
    }
}
