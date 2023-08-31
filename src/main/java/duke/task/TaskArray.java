package duke.task;
import duke.Duke;

import java.util.ArrayList;


/**
 * This class represents a list of tasks and provides methods to manipulate and display the tasks.
 */
public class TaskArray {

    private ArrayList<Task> taskArrayList;

    /**
     * Constructs an empty TaskArray.
     */
    public TaskArray() {
        taskArrayList = new ArrayList<>();
    }

    /**
     * Constructs a TaskArray with the given initial list of tasks.
     *
     * @param array The initial list of tasks to populate the TaskArray.
     */
    public TaskArray(ArrayList<Task> array) {
        taskArrayList = array;
    }

    /**
     * Prints the list of tasks along with their indexes.
     */
    public void printTaskArrayList() {
        System.out.println(Duke.HORIZONTAL_LINE);
        for(int i = 0; i < taskArrayList.size(); i++ ) {
            int index = i + 1;
            System.out.println(index +": " + taskArrayList.get(i).getTypeCheckedText());
        }
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Adds a task to the TaskArray and prints a confirmation message.
     *
     * @param task The task to be added to the TaskArray.
     */
    public void add(Task task) {
        taskArrayList.add(task);
        String word = "added:" + task.getText();
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getTypeCheckedText());
        System.out.println("Now you have "+ getTaskCount()+" tasks in the list.");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return taskArrayList.get(index);
    }

    /**
     * Returns the number of tasks in the TaskArray.
     *
     * @return The number of tasks in the TaskArray.
     */
    public int getTaskCount() {
        return taskArrayList.size();
    }

    /**
     * Removes a task from the TaskArray at the specified index and prints a confirmation message.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        if(index < 0 && index >=taskArrayList.size()){
            System.out.println(Duke.HORIZONTAL_LINE);
            System.out.println("Invalid Index");
            System.out.println(Duke.HORIZONTAL_LINE);
            return;
        }
        Task removingTask = taskArrayList.get(index);
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removingTask.getTypeCheckedText());
        taskArrayList.remove(index);
        System.out.println("Now you have "+ getTaskCount()+" tasks in the list.");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    public void printFind(String arg) {

        ArrayList<Task> tempTaskArraylist = new ArrayList<>();


        for (Task task : taskArrayList) {
            if(task.getText().contains(arg)) {
                tempTaskArraylist.add(task);
            }
        }
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < tempTaskArraylist.size(); i++) {
            int index = i + 1;
            System.out.println(index+"."+ tempTaskArraylist.get(i).getTypeCheckedText());
        }
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Returns the list of tasks in the TaskArray.
     *
     * @return The list of tasks in the TaskArray.
     */
    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }
}
