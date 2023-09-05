package seedu.duke.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.Ui.Ui;
import seedu.duke.exceptions.TaskException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;

/**
 * TaskList class
 */
public class TaskList {
    private Ui ui;
    private List<Task> taskList;

    /**
     * TaskList constructor
     *
     * @param ui Ui instance it uses to display messages to user
     */
    public TaskList(Ui ui) {
        this.ui = ui;
        this.taskList = new ArrayList<>(100);
    }

    public String exit() {
        return ui.printExit();
    }

    /**
     * Adds task to the TaskList
     *
     * @param task Task to add
     */
    public String addTask(Task task) {
        taskList.add(task);
        return ui.printAddingTask(task, taskList);
    }

    /**
     * Adds task to TaskList. Used for initial read of duke.txt, no interface/ comments to user
     *
     * @param task task to add from duke.txt
     */
    public void addAvailTasks(Task task) {
        taskList.add(task);
    }

    /**
     * uses Ui to print to user all avail tasks
     */
    public String listAllTasks() {
        return ui.printAllTasks(taskList);
    }

    /**
     * returns the current TaskList
     *
     * @return TaskList
     */
    public List<Task> returnTaskList() {
        return taskList;
    }

    /**
     * Deletes the Task at the specified index from TaskList
     *
     * @param i index of Task to be removed
     * @throws TaskException if any error
     */
    public String deleteTask(int i) throws TaskException {
        if (i > taskList.size()) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToDekete = taskList.get(i - 1);
        taskList.remove(i - 1);
        return ui.printDeleteTask(taskToDekete);
    }

    /**
     * marks task
     *
     * @param i task to mark
     * @throws TaskException error thrown
     */
    public String mark(int i) throws TaskException {
        if (i > taskList.size() || i <= 0) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToMark = taskList.get(i - 1);
        boolean beforeMarking = taskToMark.isMarked();
        if (!taskToMark.isMarked()) {
            taskToMark.mark();
        }
        return ui.printMarkTask(taskToMark, beforeMarking);
    }

    /**
     * unMarks task
     *
     * @param i task to unmark
     * @throws TaskException error thrown
     */
    public String unMark(int i) throws TaskException {
        if (i > taskList.size() || i <= 0) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToMark = taskList.get(i - 1);
        boolean beforeMarking = taskToMark.isMarked();
        if (taskToMark.isMarked()) {
            taskToMark.mark();
        }
        return ui.printUnMarkTask(taskToMark, beforeMarking);
    }

    /**
     * returns all Tasks on specified date
     *
     * @param date date to check
     */
    public String getTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getByDate().isEqual(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFromDate().isEqual(date.atStartOfDay())) {
                    tasksOnDate.add(task);
                }
            }
        }
        return ui.printTasksOnDateOrKeyword(tasksOnDate);
    }

    /**
     * returns all tasks with keyword
     *
     * @param keyword keyword to check
     * @param taskList tasklist to operate on
     */
    public String findTasks(String keyword, TaskList taskList) {
        List<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task : taskList.returnTaskList()) {
            String description = task.getDescription();
            if (description.toLowerCase().contains(keyword.toLowerCase())) {
                tasksWithKeyword.add(task);
            }
        }
        return ui.printTasksOnDateOrKeyword(tasksWithKeyword);
    }
}
