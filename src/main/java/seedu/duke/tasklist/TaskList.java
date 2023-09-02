package seedu.duke.tasklist;

import seedu.duke.Exceptions.InvalidTaskIndexException;
import seedu.duke.Exceptions.LemonException;
import seedu.duke.Tasks.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String markTask(Integer index) throws InvalidTaskIndexException {
        try {
            Task toMark = tasks.get(index);
            toMark.markAsDone();
            return toMark.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    public String unmarkTask(Integer index) throws InvalidTaskIndexException {
        try {
            Task toMark = tasks.get(index);
            toMark.markAsUndone();
            return toMark.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    public String addTasks(Task taskToAdd) {
        tasks.add(taskToAdd);
        return taskToAdd.toString();
    }

    public String deleteTask(Task taskToDelete) {
        tasks.remove(taskToDelete);
        return taskToDelete.toString();
    }

    public Integer getTasksSize() {
        return this.tasks.size();
    }

    public Task getTask(Integer i) throws InvalidTaskIndexException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("Invalid Task Number.");
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }


    public ArrayList<Task> findKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.getTaskList()){
            if (task.getDescription().contains(keyword)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
