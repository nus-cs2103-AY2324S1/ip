package duke.task;
import java.util.ArrayList;


/**
 * This class represents a list of tasks and provides methods to manipulate and display the tasks.
 */
public class TaskArray {
    private static String horiLine = "____________________________________________________________";

    private ArrayList<Task> taskArrayList;

    /**
     * Constructs an empty TaskArray.
     */
    public TaskArray(){
        taskArrayList = new ArrayList<>();
    }

    /**
     * Constructs a TaskArray with the given initial list of tasks.
     *
     * @param array The initial list of tasks to populate the TaskArray.
     */
    public TaskArray(ArrayList<Task> array){
        taskArrayList = array;
    }

    /**
     * Prints the list of tasks along with their indexes.
     */
    public void printTaskArrayList(){
        System.out.println(horiLine);
        for(int i = 0; i < taskArrayList.size(); i++ ){
            int index = i + 1;
            System.out.println(index +": " + taskArrayList.get(i).getTypeCheckedText());
        }
        System.out.println(horiLine);

    }


    /**
     * Adds a task to the TaskArray and prints a confirmation message.
     *
     * @param task The task to be added to the TaskArray.
     */
    public void add(Task task){
        taskArrayList.add(task);
        String word = "added:" + task.getText();
        System.out.println(horiLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getTypeCheckedText());
        System.out.println("Now you have "+ getTaskCount()+" tasks in the list.");
        System.out.println(horiLine);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index){
        return taskArrayList.get(index);
    }

    /**
     * Returns the number of tasks in the TaskArray.
     *
     * @return The number of tasks in the TaskArray.
     */
    public int getTaskCount(){
        return taskArrayList.size();
    }

    /**
     * Removes a task from the TaskArray at the specified index and prints a confirmation message.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index){
        if(index < 0 && index >=taskArrayList.size()){
            System.out.println(horiLine);
            System.out.println("Invalid Index");
            System.out.println(horiLine);
            return;
        }
        Task removingTask = taskArrayList.get(index);
        System.out.println(horiLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removingTask.getTypeCheckedText());
        taskArrayList.remove(index);
        System.out.println("Now you have "+ getTaskCount()+" tasks in the list.");
        System.out.println(horiLine);
    }
    /**
     * Returns the list of tasks in the TaskArray.
     *
     * @return The list of tasks in the TaskArray.
     */
    public ArrayList<Task> getTaskArrayList(){
        return taskArrayList;
    }



}
