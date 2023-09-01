package haste.data;

import haste.exceptions.EmptyTaskException;
import haste.exceptions.InvalidCommand;
import haste.tasks.Task;

import java.util.ArrayList;


public class TaskList {
    public static final ArrayList<Task>  taskList = new ArrayList<>();
    // number tracker
    public static int numOfTasks = 0;
    // adds task into task list
    public static void addTask(Task newTask) throws InvalidCommand, EmptyTaskException {
        taskList.add(newTask);
        numOfTasks++;
    }

    public static void deleteTask(int id) {
        taskList.remove(id);
        numOfTasks--;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }


    public static Task getTask(int id) {
        return taskList.get(id);
    }
}
