package duke.tasks;

import duke.tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a tasklist
 */
public class DukeList {
    private ArrayList<Task> dukeList;

    public DukeList() {
        dukeList = new ArrayList<>(100);
    }



    public DukeList(ArrayList<Task> ItemList) {
        dukeList = ItemList;
    }

    /**
     * This method simply prints out an acknowledgemnt that a task has been added
     * @param newTask takes in a new task
     */

    public void add(Task newTask) {
        this.dukeList.add(newTask);
    }

    /**
     * This method deletes a task from the tasklist
     * @param taskNum index of the task
     */
    public void deleteTask(int taskNum) {
        dukeList.remove(taskNum - 1);
    }

    public ArrayList<Task> filterByKeyword(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : dukeList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Sets task as done
     * @param taskNum index of task
     */
    public void setTaskAsDone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsDone();
    }

    /**
     * Sets task as undone
     * @param taskNum index of task
     */
    public void setTaskAsUndone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsUndone();
    }

    /**
     * returns size of list
     * @return size of list
     */
    public int getSize() {
        return dukeList.size();
    }

    public ArrayList<Task> getArrayList() {
        return this.dukeList;
    }

    public Task getTask(int index) {
        return dukeList.get(index);
    }
}
