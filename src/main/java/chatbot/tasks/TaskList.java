package chatbot.tasks;

import java.util.ArrayList;

/**
 * Represents a task list
 */
public class TaskList {
    /**
     * List of tasks stored in the task list
     */
    ArrayList<Task> inputs = new ArrayList<>();

    /**
     * Constructor of TaskList
     * @param inputs The initial list of tasks in the task list
     */
    public TaskList(ArrayList<Task> inputs){
        this.inputs = inputs;
    }

    /**
     * Adds the task to the task list
     * @param task Task to be added to the task list
     */
    public void addTask(Task task){
        this.inputs.add(task);
    }

    /**
     * Removes the task at a particular index as done
     * @param num Index of the particular task in the task list
     */
    public void removeTask(int num){
        if(num < 0){
            System.out.println("The value cannot be negative. Please try again.");
        } else if (num >= this.inputs.size()){
            System.out.println("The input value is wrong. Please try again.");
        } else {
            this.inputs.remove(num);
        }
    }

    /**
     * Marks the task at a particular index as done
     * @param num Index of the particular task in the task list
     */
    public void markTask(int num){
        if(num < 0){
            System.out.println("The value cannot be negative. Please try again.");
        } else if (num >= this.inputs.size()){
            System.out.println("The input value is wrong. Please try again.");
        } else {
            this.inputs.get(num).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + inputs.get(num));
        }
    }

    /**
     * Unmarks the task at a particular index as done
     * @param num Index of the particular task in the task list
     */
    public void unMarkTask(int num){
        if(num < 0){
            System.out.println("The value cannot be negative. Please try again.");
        } else if (num >= this.inputs.size()){
            System.out.println("The input value is wrong. Please try again.");
        } else {
            this.inputs.get(num).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + inputs.get(num));
        }
    }

    /**
     * Returns the number of tasks in the task list
     * @return Number of tasks in the task list
     */
    public int len(){
        return this.inputs.size();
    }

    /**
     * Retrieves the list of tasks stored in the task list
     * @return ArrayList that contains the tasks stored in the task list
     */
    public ArrayList<Task> retrieveList(){
        return this.inputs;
    }
}
