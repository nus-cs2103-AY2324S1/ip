package teho.main;

import teho.main.Task;

import java.util.ArrayList;
//contains the task list
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
   }

    public int getSize() {
        return this.taskList.size();
    }
    public Task getTask(int taskNumber) {
        return this.taskList.get(taskNumber);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }
    public void remove(int taskNumber) {
        this.taskList.remove(taskNumber);
    }
}
