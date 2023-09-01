package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        return tasks.get(i);
    }

    public void addTask(Task task) {

        tasks.add(task);
    }

    public void markTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        tasks.get(i).unmark();
    }

    public void deleteTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        tasks.remove(i);
    }

    public TaskList findTasks(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task task: tasks) {
            if (task.isContains(keyword)) {
                newTaskList.addTask(task);
            }
        }
        return newTaskList;
    }
}
