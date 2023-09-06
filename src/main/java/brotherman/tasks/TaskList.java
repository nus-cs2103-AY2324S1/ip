package brotherman.tasks;

import java.util.ArrayList;
/**
 * Represents a list of tasks
 */
public class TaskList {

    /**
     * List of tasks
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     * @param taskList List of tasks
     */
    public TaskList(ArrayList taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list
     * @param task Task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param num Task number to be deleted
     */
    public void delete(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            System.out.println(
                            "The task is now deleted Brotherman \n"
                            + taskList.get(num).toString()
            );
            taskList.remove(num);
        }
    }


    /**
     * Returns the task list
     * @return Task list
     */
    public ArrayList list() {

        return this.taskList;
    }

    /**
     * Returns the size of the task list
     * @return size of the task list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks the task at the specified index as done
     * @param num Index of the task
     */
    public void markDone(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            taskList.get(num).markAsDone();
            System.out.println(
                    "The task is done Brotherman \n"
                    + taskList.get(num).toString()
            );
        }
    }

    /**
     * Marks the task at the specified index as not done
     * @param num Index of the task
     */
    public void markUndone(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            taskList.get(num).unmarkAsDone();
            System.out.println(
                    "The task is now undone Brotherman \n"
                    + taskList.get(num).toString()
            );
        }
    }

    /**
     * Returns the tasks that have the specified keyword
     * @param keyword Keyword of the task
     * @return Tasks that have the specified keyword
     */
    public ArrayList<Task> getTasksByKeyword(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task listItems : taskList) {
            if (listItems.description.contains(keyword)) {
                list.add(listItems);
            }
        }

        return list;
    }
}
