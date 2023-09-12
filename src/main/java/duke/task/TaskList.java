package duke.task;

import duke.task.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a list of tasks and provides methods to interact.
 * It can add, retrieve, remove tasks and keep track of the task count.
 */
public class TaskList {
    private List<Task> taskList;
    private int taskCount;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(){
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Construct a TaskList if there are existing tasks in folder
     *
     * @param existingTasks The existing list of tasks that was saved.
     */
    public TaskList(List<Task> existingTasks){
        taskList = new ArrayList<>(existingTasks);
        taskCount = taskList.size();
    }

    /**
     * Adds a task to the task list.
     * Increments the task count by 1 each time .
     *
     * @param task The task to be addded.
     */
    public void addTask(Task task){
        taskList.add(taskCount, task);
        taskCount++;
    }


    public List<Task> getTaskList(){
        return taskList;
    }

    public int getTask_Count(){
        return taskList.size();
    }

    public Task getTask(int task_number){
        return taskList.get(task_number - 1);
    }

    /**
     * Removes a task from the TaskList based on its index.
     *
     * @param task_number The index of the task to be removed.
     */
    public void removeTask(int taskNumber) {
        boolean isValidTaskNumber = taskNumber >= 0;
        boolean isTaskNumberInList = taskNumber <= taskList.size();

        if (isValidTaskNumber && isTaskNumberInList) {
            taskList.remove(taskNumber - 1);
        }
    }

    /**
     * Find the tasks that contains the keyword and put them in a List</Task>.
     *
     * @param keyword Find tasks that contains this keyword.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if(task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
