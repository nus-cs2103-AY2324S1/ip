package duke;

import duke.task.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> task_List;
    private int task_Count;

    public TaskList(){
        task_List = new ArrayList<>();
        task_Count = 0;
    }

    public TaskList(List<Task> existingTasks){
        task_List = new ArrayList<>(existingTasks);
        task_Count = task_List.size();
    }

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

    public void removeTask(int task_number) {
        if (task_number >= 0 && task_number <= task_List.size()) {
            task_List.remove(task_number - 1);
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
        for (Task task : task_List) {
            if(task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
