package haste.data;

import haste.exceptions.EmptyTaskException;
import haste.exceptions.InvalidCommand;
import haste.tasks.Task;

import java.util.ArrayList;


public class TaskList {
    public ArrayList<Task> taskList;
    // number tracker
    public static int numOfTasks;

    public TaskList() {
        taskList = new ArrayList<Task>();
        numOfTasks = 0;
    }
    // adds task into task list
    public void addTask(Task newTask) {
        taskList.add(newTask);
        numOfTasks++;
    }

    public void deleteTask(int id) {
        taskList.remove(id);
        numOfTasks--;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }


    public Task getTask(int id) {
        return taskList.get(id);
    }
}
