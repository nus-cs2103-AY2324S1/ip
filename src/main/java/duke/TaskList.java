package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> taskList;

    public TaskList(DataStorage store) {
        taskList = store.taskData;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.remove(taskIndex);
        }
    }

    public void mark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsDone();
        }
    }

    public void unmark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsUndone();
        }
    }

    /**
     * Filters taskList to find tasks containing given keyword.
     *
     * @param keyword String keyword to find tasks containing it.
     * @return ArrayList containing tasks with given keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                filteredList.add(taskList.get(i));
            }
        }
        return filteredList;
    }

}
