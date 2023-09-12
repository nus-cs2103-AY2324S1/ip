package chatbot.tasks;

import java.util.ArrayList;

/**
 * Represents a task list
 */
public class TaskList {
    /**
     * List of tasks stored in the task list
     */
    private ArrayList<Task> inputs = new ArrayList<>();

    /**
     * Constructor of TaskList
     * @param inputs The initial list of tasks in the task list
     */
    public TaskList(ArrayList<Task> inputs) {
        this.inputs = inputs;
    }

    /**
     * Adds the task to the task list
     * @param task Task to be added to the task list
     */
    public void addTask(Task task) {
        this.inputs.add(task);
    }

    /**
     * Removes the task at a particular index as done
     * @param num Index of the particular task in the task list
     */
    public String removeTask(int num) {
        if (num < 0) {
            return "The value cannot be negative. Please try again.";
        } else if (num >= inputs.size()) {
            return "The input value is wrong. Please try again.";
        } else {
            inputs.remove(num);
            return "The task has been deleted. \n"
                    + inputs.get(num).toString();
        }
    }

    /**
     * Marks the task at a particular index as done
     * @param num Index of the particular task in the task list
     */
    public String markTask(int num) {
        if (num < 0) {
            return "The value cannot be negative. Please try again.";
        } else if (num >= inputs.size()) {
            return "The input value is wrong. Please try again.";
        } else {
            inputs.get(num).markAsDone();
            String output = "Nice! I've marked this task as done:"
                    + "  " + inputs.get(num);

            return output;
        }
    }

    /**
     * Unmarks the task at a particular index as done
     * @param num Index of the particular task in the task list
     */
    public String unMarkTask(int num) {
        if (num < 0) {
            return "The value cannot be negative. Please try again.";
        } else if (num >= inputs.size()) {
            return "The input value is wrong. Please try again.";
        } else {
            inputs.get(num).markAsUndone();
            String output = "OK, I've marked this task as not done yet:"
                    + "  " + inputs.get(num);

            return output;
        }
    }

    /**
     * Returns the number of tasks in the task list
     * @return Number of tasks in the task list
     */
    public int len() {
        return inputs.size();
    }

    /**
     * Retrieves the list of tasks stored in the task list
     * @return ArrayList that contains the tasks stored in the task list
     */
    public ArrayList<Task> retrieveList() {
        return inputs;
    }

    public ArrayList<Task> getFilteredTasks(String input){
        ArrayList<Task> output = new ArrayList<>();

        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).description.contains(input)) {
                output.add(inputs.get(i));
            }
        }
        return output;
    }
}
