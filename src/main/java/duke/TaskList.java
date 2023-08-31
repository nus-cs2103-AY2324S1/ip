package duke;

import duke.task.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a list of tasks and provides methods to interact.
 * It can add, retrieve, remove tasks and keep track of the task count.
 */
public class TaskList {
    private List<Task> task_List;
    private int task_Count;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(){
        task_List = new ArrayList<>();
        task_Count = 0;
    }

    /**
     * Construct a TaskList if there are existing tasks in folder
     *
     * @param existingTasks The existing list of tasks that was saved.
     */
    public TaskList(List<Task> existingTasks){
        task_List = new ArrayList<>(existingTasks);
        task_Count = task_List.size();
    }

    /**
     * Adds a task to the task list.
     * Increments the task count by 1 each time .
     *
     * @param task The task to be addded.
     */
    public void addTask(Task task){
        task_List.add(task_Count, task);
        task_Count++;
    }


    public List<Task> getTask_List(){
        return task_List;
    }

    public int getTask_Count(){
        return task_List.size();
    }

    public Task getTask(int task_number){
        return task_List.get(task_number - 1);
    }

    /**
     * Removes a task from the TaskList based on its index.
     *
     * @param task_number The index of the task to be removed.
     */
    public void removeTask(int task_number) {
        if (task_number >= 0 && task_number <= task_List.size()) {
            task_List.remove(task_number - 1);
        }
    }

}
