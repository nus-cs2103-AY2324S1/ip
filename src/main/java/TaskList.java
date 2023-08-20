import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasklist;

    TaskList() {
        this.tasklist = new ArrayList<Task>();
    }
    // adding task into tasklist and output the relevant information
    void add(Task task) {
        this.tasklist.add(task);
        System.out.println("Got it. I've added this task:\n" +
                task.toString() + "\nNow you have " +
                tasklist.size() + " tasks in the list.");

    }

    void mark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task markedTask = this.tasklist.get(taskIndex).done();
        System.out.println("Nice! I've marked this task as done:\n" + markedTask.toString());
        tasklist.set(taskIndex, markedTask);
    }

    void unMark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task unmarkedTask = this.tasklist.get(taskIndex).undone();
        System.out.println("OK, I've marked this task as not done yet:\n" + unmarkedTask.toString());
        tasklist.set(taskIndex, unmarkedTask);
    }

    void listContent() {
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ": " + tasklist.get(i).toString());
        }
    }



}
