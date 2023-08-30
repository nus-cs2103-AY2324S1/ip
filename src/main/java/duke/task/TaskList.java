package duke.task;
import java.util.ArrayList;

/**
 * TaskList is the class to store the lists and carry out operands on the tasks
 */
public class TaskList {

    /**
     * field tasklist stores the ArrayList of tasks
     * field lines stores the String of lines
     */
    ArrayList<Task> tasklist;
    String LINES;

    /**
     * constructor for TaskList and initiates the lines
     */
    public TaskList() {
    }

    /**
     * Constructor for TaskList if its able to load the tasks from txt file
     * @param prevTasks loads the previous tasks from the txt file
     */
    public TaskList(ArrayList<Task> prevTasks) {
        this.tasklist = prevTasks;
    }

    /**
     *
     * @param task takes in the task generated and stores into the ArrayList
     */
    public void addTask(Task task) {
        tasklist.add(task);
    }

    /**
     *
     * @param taskNumber takes in the index of the task to be marked as completed
     */

    public void markTask(int taskNumber) {
        Task wantedtask = this.tasklist.get(taskNumber - 1); //account for 0 indexing
        wantedtask.markCompleted();
    }

    /**
     *
     * @param taskNumber takes in the index of the tasknumber to be marked as uncompleted
     */
    public void unmarkTask(int taskNumber) {
        Task wantedtask = this.tasklist.get(taskNumber - 1); //account for 0 indexing
        wantedtask.markUncompleted();
    }

    /**
     *
     * @param tasknumber takes in the index of the tasknumber to be deleted
     */

    public void deleteTask(int tasknumber) {
        Task wantedtask = this.tasklist.get(tasknumber - 1);
        this.removeTask(tasknumber - 1); //this would also be the line number to delete in the txt file
    }

    /**
     *
     * @param taskNumber removes task at the given index
     */

    private void removeTask(int taskNumber) {
        this.tasklist.remove(taskNumber);
    }
    public String findTasks(String keyword) {
//        System.out.println(this.LINES);
        String res = "";
        int foundcount = 0;
        for (int i = 0; i < tasklist.size(); i ++) {
            String check = tasklist.get(i).toString();
            if (check.contains(keyword)) {
                res += "\n";
                res += (foundcount + ". " + check);
                foundcount += 1;
            }
        }
        return res;
    }

    /**
     *
     * @return String format for the TaskList given to be procesed by txt file
     */

    public String totxtformat() {
        String res = "";
        int taskNumber = 1;
        while (taskNumber < tasklist.size() + 1) {
            Task task = tasklist.get(taskNumber - 1);
            String txtformat = task.completed + "|" + task.toString() + "|" + task.type + "|" + task.ogName + "\n";
            res += txtformat;
            taskNumber++;
        }
        return res;
    }

    public int size() {
        return this.tasklist.size();
    }

    public Task getTask(int taskNum) {
        return this.tasklist.get(taskNum);
    }

}
