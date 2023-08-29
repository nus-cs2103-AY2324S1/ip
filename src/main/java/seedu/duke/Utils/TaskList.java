package seedu.duke.Utils;

import seedu.duke.Exceptions.TaskException;
import seedu.duke.Ui;

import seedu.duke.Tasks.Task;
import seedu.duke.Tasks.Deadline;
import seedu.duke.Tasks.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Ui ui;
    private List<Task> taskList;

    public TaskList(Ui ui) {
        this.ui = ui;
        this.taskList = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        taskList.add(task);
        ui.printAddingTask(task, taskList);
    }

    public void addAvailTasks(Task task) {
        taskList.add(task);
    }

    public void listAllTasks() {
        ui.printAllTasks(taskList);
    }

    public List<Task> returnTaskList() {
        return taskList;
    }

    public void deleteTask(int i) throws TaskException {
        if (i > taskList.size()) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToDekete = taskList.get(i - 1);
        taskList.remove(i - 1);
        ui.printDeleteTask(taskToDekete);
    }

    public void mark(int i) throws TaskException {
        if (i > taskList.size() || i <= 0) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToMark = taskList.get(i - 1);
        boolean beforeMarking = taskToMark.isMarked();
        if (!taskToMark.isMarked()) {
            taskToMark.mark();
//            taskToMark.isMarked = true;
        }
        ui.printMarkTask(taskToMark, beforeMarking);
    }

    public void unMark(int i) throws TaskException {
        if (i > taskList.size() || i <= 0) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToMark = taskList.get(i - 1);
        boolean beforeMarking = taskToMark.isMarked();
        if (taskToMark.isMarked()) {
//            taskToMark.isMarked = false;
            taskToMark.mark();
        }
        ui.printUnMarkTask(taskToMark, beforeMarking);
    }

    public void getTasksOnDate(LocalDate date) {
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
        ui.printTasksOnDate(tasksOnDate);
    }
}
