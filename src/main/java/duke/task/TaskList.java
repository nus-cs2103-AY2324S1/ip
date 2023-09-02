package duke.task;

import java.util.ArrayList;
import duke.main.DateFormatter;

/** Class to represent the taskList of the user*/
public class TaskList {

    /** The list to store the tasks*/
    private ArrayList<Task> taskList;

    /**
     * A formatter for the date
     */
    private static final DateFormatter DF = new DateFormatter();

    /**
     * A constructor for the TaskList
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Returns size of taskList
     */
    public int size() {
        return this.getTaskList().size();
    }

    /**
     * Returns taskList
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds task to taskList
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.getTaskList().add(task);
    }

    /**
     * Returns task from taskList
     * @param index The index of the task inside the taskList to be returned
     * @return task
     */
    public Task getTask(int index) {
        return this.getTaskList().get(index);
    }

    /**
     * Removes task from taskList
     * @param index The index of the task inside the taskList to be removed
     */
    public void removeTask(int index) {
        this.getTaskList().remove(index);
    }

    /**
     * Returns taskList in String format to be written to file
     * @return content of taskList
     */
    public String convertToFileContent() {
        String result = "";
        String line = "";
        for (int i = 0; i < this.size(); i++) {
            Task currentTask = this.getTask(i);
            if (currentTask instanceof Todos) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description;
            } else if (currentTask instanceof Deadlines) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description + " | " +
                        DF.saveDateToFile(((Deadlines) currentTask).endDate);
            } else if (currentTask instanceof Events) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description + " | " +
                        DF.saveDateToFile(((Events) currentTask).startDate) + " | " +
                        DF.saveDateToFile(((Events) currentTask).endDate);
            }
            result += line + "\n";
        }
        return result;
    }

}
