package aj;

import java.util.List;

/**
 * TaskList wrapper class to integrate with Storage class.
 */
public class TaskList {
    List<Task> taskList;

    public Task getTask(int idx) {
        return this.taskList.get(idx);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void removeTask(int idx) {
        this.taskList.remove(idx);
    }


    public int getSize() {
        return this.taskList.size();
    }

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
