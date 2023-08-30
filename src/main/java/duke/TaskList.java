package duke;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task, Ui ui) {
        taskList.add(task);
        ui.showAddTask(task, taskList.size());
    }

    public void listTask(Ui ui) {
        if (taskList.isEmpty()) {
            ui.showError("OOPS!!! There is nothing in the list, yet!");
            return;
        }
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = taskList.get(i).toString();
        }
        ui.listTask(tasks);
    }

    public void printDateTask(Keyword key, LocalDate date, Ui ui) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! There is nothing in the list, yet!");
        } else {
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
                ui.showError(String.format("OOPS!!! There is nothing happening on %s.",
                        date.format(Time.DATE_DISPLAY_FORMATTER)));
            }
        }
    }

    public void deleteTask(int index, Ui ui) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            listTask(ui);
            throw new DukeException(String.format("OOPS!!! There is no task %d to delete", index + 1));
        }
        Task removedTask = taskList.remove(index);
        ui.showDeleteTask(removedTask, taskList.size());
    }

    public void markTask(int index, boolean isMark, Ui ui) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            String err = String.format("OOPS!!! There is no task %d to %s",
                     index + 1, isMark ? "mark" : "unmark");
            listTask(ui);
            throw new DukeException(err);
        }
        ui.showMarkTask(isMark, taskList.get(index).mark(isMark));
    }

    public void manipulateAllTask(Keyword key, Ui ui) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(String.format("OOPS!!! There are no tasks to %s.", key.getKeyword()));
        }
        if (key.equals(Keyword.DELETE)) {
            taskList.clear();
        } else {
            taskList.forEach(t -> t.mark(key.equals(Keyword.MARK)));
        }
        ui.showManipulateAllTask(key.getKeyword());
    }
}
