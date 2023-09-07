package duke.task;

import duke.exception.ChatException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int taskNumber) throws ChatException {
        if(taskList.size() == 0) {
            throw new ChatException("☹ OOPS!!! The list is empty.");
        } else if (taskNumber <= 0 || taskNumber > taskList.size()) {
            throw new ChatException("☹ OOPS!!! Invalid task number inputted.");
        } else {
            return taskList.get(taskNumber - 1);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskNumber) throws ChatException {
        taskList.remove(taskNumber - 1);
    }

    public void markDone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }

    public void markUndone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }

    public TaskList findTask(String keyword) {
        TaskList foundList = new TaskList();
        for (Task task : taskList) {
            if (task.isFound(keyword)) {
                foundList.addTask(task);
            }
        }
        return foundList;
    }
}
