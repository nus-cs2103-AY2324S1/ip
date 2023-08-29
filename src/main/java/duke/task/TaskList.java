package duke.task;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    /**
     * field tasklist stores the ArrayList of tasks
     * field lines stores the String of lines
     */
    ArrayList<Task> tasklist;
    String lines;

    /**
     * constructor for TaskList
     */
    public TaskList() {
        this.lines = "______________________________";
    }

    /**
     *
     * @param prevTasks loads the previous tasks from the txt file
     */
    public TaskList(ArrayList<Task> prevTasks) {
        this.tasklist = prevTasks;
        this.lines = "______________________________";
    }

    /**
     *
     * @param task takes in the task generated and stores into the ArrayList
     */
    public void addTask(Task task) {
        tasklist.add(task);
        System.out.println("MEOW got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + this.tasklist.size() + " meow-tasks in the list.");
        System.out.println(lines);
    }

    /**
     *
     * @param tasknumber takes in the index of the task to be marked as completed
     */

    public void markTask(int tasknumber) {
        Task wantedtask = this.tasklist.get(tasknumber - 1); //account for 0 indexing
        wantedtask.markCompleted();
        System.out.println("Nice! I've meowrked this task as done: ");
        System.out.println("   " + wantedtask);
        System.out.println(lines);

    }

    /**
     *
     * @param tasknumber takes in the index of the tasknumber to be marked as uncompleted
     */
    public void unmarkTask(int tasknumber) {
        Task wantedtask = this.tasklist.get(tasknumber - 1); //account for 0 indexing
        wantedtask.markUncompleted();
        System.out.println("Ok, get your task done soon, I'll be waiting!");
        System.out.println(" " + wantedtask);
        System.out.println(lines);

    }

    /**
     *
     * @param tasknumber takes in the index of the tasknumber to be deleted
     */

    public void deleteTask(int tasknumber) {
        Task wantedtask = this.tasklist.get(tasknumber - 1);
        this.removeTask(tasknumber - 1); //this would also be the line number to delete in the txt file
        System.out.println("Meow... ok, I've removed this task: ");
        System.out.println(" " + wantedtask);
        System.out.println("Now you have " + this.tasklist.size() + " meow-tasks in the list.");
        System.out.println(lines);
    }
    /**
     * displays the tasks in a proper format for the String format
     */
    public void display() {
        System.out.println(lines);
        System.out.println("Meoowww here are your tasks");
        for (int i = 1; i < this.tasklist.size() + 1; i++) {
            System.out.println(i + ". " + this.tasklist.get(i - 1));
        }
        System.out.println(lines);
    }

    /**
     *
     * @param taskNumber removes task at the given index
     */

    private void removeTask(int taskNumber) {
        this.tasklist.remove(taskNumber);
    }
    public void findTasks(String keyword) {
        System.out.println(lines);
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
        if (foundcount == 0) System.out.println("Meow :( found no tasks with " + keyword);
        else System.out.println("Meow Here are your matching tasks !\n" + res);
        System.out.println(lines);
    }

//    public int size() {
//        return tasklist.size();
//    }
//
//    public Task get(int num) {
//        return tasklist.get(num - 1);
//    }

    /**
     *
     * @return String format for the TaskList given
     */

    public String totxtformat() {
        String res = "";
        int taskNumber = 1;
        while (taskNumber < tasklist.size() + 1) {
            Task task = tasklist.get(taskNumber - 1);
            String txtformat = task.completed + "|" + task.toString() + "|" + task.type + "|" + task.ogname + "\n";
            res += txtformat;
            taskNumber++;
        }
        return res;
    }

}
