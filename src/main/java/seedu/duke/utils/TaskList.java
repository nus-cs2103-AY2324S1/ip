package seedu.duke.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.exceptions.TaskException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;
import seedu.duke.ui.Ui;

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
    public TaskList(List<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
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
        int before = taskList.size();
        taskList.add(task);
        assert taskList.size() > before : "size did not increase";
        return ui.printAddingTask(task, taskList);
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
        Task taskToDelete = taskList.get(i - 1);
        int before = taskList.size();
        taskList.remove(i - 1);
        assert taskList.size() < before : "size did not decrease, did not delete";
        return ui.printDeleteTask(taskToDelete);
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
