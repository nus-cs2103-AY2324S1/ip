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
    public String printTaskArrayList() {
        String output ="";
        for(int i = 0; i < taskArrayList.size(); i++ ) {
            int index = i + 1;
            output += "\n"+index +": " + taskArrayList.get(i).getTypeCheckedText();
        }
        return output;
    }

    /**
     * Adds a task to the TaskArray and prints a confirmation message.
     *
     * @param task The task to be added to the TaskArray.
     */
    public String add(Task task) {
        taskArrayList.add(task);
        String word = "Got it. I've added this task:\n" + task.getTypeCheckedText();
        word += "\nNow you have "+ getTaskCount()+" tasks in the list.";
        return word;
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
    public String removeTask(int index) {
        String output = "";
        if(index < 0 && index >=taskArrayList.size()){
            output = "Invalid Index";
            return output;
        }
        Task removingTask = taskArrayList.get(index);
        taskArrayList.remove(index);
        output = "Noted. I've removed this task:\n" + removingTask.getTypeCheckedText() +"\nNow you have "+ getTaskCount()+" tasks in the list.";
        return output;
    }

    public String printFind(String arg) {

        ArrayList<Task> tempTaskArraylist = new ArrayList<>();


        for (Task task : taskArrayList) {
            if(task.getText().contains(arg)) {
                tempTaskArraylist.add(task);
            }
        }

        String output = "Here are the matching tasks in your list:";
        for(int i = 0; i < tempTaskArraylist.size(); i++) {
            int index = i + 1;
            output += ("\n" + index+"."+ tempTaskArraylist.get(i).getTypeCheckedText());
        }

        return output;
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
