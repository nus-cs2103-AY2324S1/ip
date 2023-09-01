package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public static TaskList of(Task ... args) {
        TaskList taskList = new TaskList();
        for (Task task: args) {
            taskList.addToList(task);
        }
        return taskList;
    }

    public void addToList(Task s) {
        this.list.add(s);
    }

    public Task getTaskAt(int index) {
        return this.list.get(index);
    }

    public int getNumberOfTasks() {
        return this.list.size();
    }

    public void deleteTaskAt(int index) {
        this.list.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            s.append((i + 1) + "." + this.list.get(i));
            if (i < this.list.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TaskList)) {
            return false;
        } else {
            TaskList taskList = (TaskList) o;
            if (taskList.getNumberOfTasks() == this.getNumberOfTasks()) {
                for (int i = 0; i < this.getNumberOfTasks(); i++) {
                    if (!(taskList.getTaskAt(i).equals(this.getTaskAt(i)))) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }
}
