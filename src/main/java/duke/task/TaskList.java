package duke.task;

import duke.Parser;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;
    protected static Ui ui = new Ui();
    protected int size = 0;
    protected static int max = 100;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>(max);
    }

    private Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Sets the taskList to point to a new taskArray
     * @param taskArrayList
     */
    public void setTasks(ArrayList<Task> taskArrayList) {
        this.tasks = taskArrayList;
        this.size = taskArrayList.size();
    }

    public int getSize() {
        return this.size;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.size++;
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.size--;
    }

    public String getSearchTask(String searchString) {
        String result = "";
        Parser parser = new Parser();
        for (Task task : tasks) {
            if (parser.parseSearch(task.getDescription(), searchString)) {
                result += task + "\n";
            }
        }
        return result;
    }

    public void setTaskDone(int index, boolean done) {
        this.tasks.get(index).setDone(done);
    }

    @Override
    public String toString() {
        if (this.size == 0) { return ""; }
        String response = "";
        for (int i = 0; i < this.size; i++) {
            response += String.format("%d. %s\n", i+1, this.getTask(i));
        }
        return response.substring(0, response.length() - 1);
    }

    /**
     * Generates the savable format of the tasks in the array to be written to a file and obtained at a later date
     * @return the savable string
     */
    public String toTaskSave() {
        String response = "";
        for (int i = 0; i < this.size; i++) {
            response += String.format("%s\n", this.tasks.get(i).toSave());
        }
        return response.substring(0, response.length() - 1);
    }
}
