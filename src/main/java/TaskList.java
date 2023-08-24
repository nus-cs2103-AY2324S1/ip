import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void checkIndex(int taskIdx) throws IllegalValueException {
        if(taskIdx < 0 || taskIdx >= tasks.size()) {
            throw new IllegalValueException("☹ OOPS!!! Please enter a valid task number.");
        }
    }

    public Task markTask(int taskIdx) throws IllegalValueException {
        try {
            checkIndex(taskIdx);
        } catch (IllegalValueException e) {
            throw e;
        }
        Task currTask = tasks.get(taskIdx);
        currTask.markDone();
        return currTask;
    }

    public Task unmarkTask(int taskIdx) throws IllegalValueException {
        try {
            checkIndex(taskIdx);
        } catch (IllegalValueException e) {
            throw e;
        }
        Task currTask = tasks.get(taskIdx);
        currTask.markUndone();
        return currTask;
    }

    public Task deleteTask(int taskIdx) throws IllegalValueException {
        try {
            checkIndex(taskIdx);
        } catch (IllegalValueException e) {
            throw e;
        }
        Task currTask = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        return currTask;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String text = "Here are the tasks in your list:";
        for(int i = 0; i < tasks.size(); i++) {
            text += "\n\t" + (i + 1) + "." + tasks.get(i).toString();
        }
        return text;
    }

    public String toStringStorage() {
        String s = "";
        for(Task task: tasks) {
            s += task.toStringStorage() + "\n";
        }
        return s;
    }
}
