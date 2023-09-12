package chatty.task;

import chatty.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int listSize() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int i) throws InvalidTaskNumberException {
        if (i >= this.listSize() || i < 0) {
            throw new InvalidTaskNumberException();
        }
        return this.taskList.remove(i - 1);
    }

    public void markTask(int i, boolean status) throws InvalidTaskNumberException {
        if (i >= this.listSize() || i < 0) {
            throw new InvalidTaskNumberException();
        }
        Task taskToMark = this.taskList.get(i - 1);
        taskToMark.markStatus(status);
    }

    public String showTask(int i) {
        Task taskToShow = taskList.get(i - 1);
        return taskToShow.toString();
    }

    public String containsKeyword(String keyword) {
        StringBuilder listWithKeyword = new StringBuilder();
        int i = 0;
        while (i < taskList.size()) {
            if (taskList.get(i).checkKeyword(keyword)) {
                int taskNumber = i + 1;
                listWithKeyword.append(taskNumber + ". " + this.showTask(taskNumber)).append('\n');
            }
            i++;
        }
        return listWithKeyword.toString();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

}
