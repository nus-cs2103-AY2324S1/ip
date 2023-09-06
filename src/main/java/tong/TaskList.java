package tong;

import tong.exception.TaskNotFoundException;
import tong.task.Task;
import tong.exception.DuplicatedMarkException;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "Oops! Your to-do list is empty. Add some tasks now:)";
        }

        String result = "YOUR TO-DO LIST:\n";
        for (int i = 0; i < this.taskList.size(); i++) {
            int order = i + 1;
            result+= order + ". " + taskList.get(i) +"\n";
        }
        result+= "----------END OF YOUR TO-DO LIST----------\n";
        result+= taskList.size() + " tasks in total";
        return result;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int targetVisibleIndex) throws TaskNotFoundException {
        int targetInvisibleIndex = targetVisibleIndex - 1;
        if (targetInvisibleIndex >= taskList.size()) {
            throw new TaskNotFoundException("Input task index out of bound.");
        }
        return taskList.get(targetInvisibleIndex);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void markTask(Task task) throws DuplicatedMarkException {
        task.markAsDone();
    }

    public void unmarkTask(Task task) throws DuplicatedMarkException {
        task.markAsNotDone();
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }
}
