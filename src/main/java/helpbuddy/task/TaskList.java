package helpbuddy.task;

import helpbuddy.exception.HelpBuddyException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int i) {
        this.taskList.remove(i);
    }

    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Finds Task objects that has String s in description of Task.
     * @param s String that represents the description of Task to find in taskList.
     * @return an ArrayList<Task> with Task objects description matching String s.
     */
    public ArrayList<Task> findCommonTask(String s) {
        ArrayList<Task> commonTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasDescription(s)) {
                commonTaskList.add(task);
            }
        }
        return commonTaskList;
    }
}
