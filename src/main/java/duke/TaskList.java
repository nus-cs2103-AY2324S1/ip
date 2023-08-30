package duke;

import duke.DukeException;
import duke.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list == null) {
            throw new DukeException("Empty taskList");
        } else {
            this.list = list;
        }
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    };

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int index) {
        Task removedTask = list.remove(index);
        return removedTask;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

}
