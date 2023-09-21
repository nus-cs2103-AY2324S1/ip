package duke.task;

import java.util.ArrayList;

import duke.main.DateFormatter;

/** Class to represent the taskList of the user*/
public class TaskList {

    /**
     * A formatter for the date
     */
    private static final DateFormatter DF = new DateFormatter();
    /** The list to store the tasks*/
    private final ArrayList<Task> taskList;

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
     * Inserts task to specific position of the taskList
     * @param index The index in which the task is to be inserted in the taskList
     * @param task The task to be added
     */
    public void insertTask(int index, Task task) {
        this.getTaskList().add(index, task);
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
     * @return content of taskList in String format
     */
    public String convertToFileContent() {
        String result = "";
        String line = "";
        for (int i = 0; i < this.size(); i++) {
            Task currentTask = this.getTask(i);
            if (currentTask instanceof Todo) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description;
            } else if (currentTask instanceof Deadline) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description + " | "
                        + DF.saveDateToFile(((Deadline) currentTask).endDate);
            } else if (currentTask instanceof Event) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description + " | "
                        + DF.saveDateToFile(((Event) currentTask).startDate) + " | "
                        + DF.saveDateToFile(((Event) currentTask).endDate);
            }
            result += line + "\n";
        }
        return result;
    }

}
