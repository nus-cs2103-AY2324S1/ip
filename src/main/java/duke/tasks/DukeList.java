package duke.tasks;

import duke.tasks.Task;

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
        Task removedItem = dukeList.remove(taskNum - 1);
    }

    /**
     * Displays the current list
     */
    public void displayList() {
        System.out.println("Here are the tasks in your list:");
        int len = dukeList.size();
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Task currTask = dukeList.get(i);
            System.out.println(num + ". " + currTask.toString());
        }
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
