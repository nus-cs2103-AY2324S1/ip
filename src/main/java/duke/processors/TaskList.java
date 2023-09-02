package duke.processors;

import duke.task.Task;
import java.util.ArrayList;
public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();
    private int count = 0;

    public TaskList(FileHandler duke) {
        duke.readFile(tasks);
        count = tasks.size();
    }

    public Task get(int ind) {

        return this.tasks.get(ind);
    }

    public void addTasks(Task task) {
        this.tasks.add(task);
        this.count++;
    }

    public void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }

    public void deleteTask(int index, FileHandler duke) {
        Task delete = this.tasks.remove(index);
        this.count--;

        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + delete.toString());
        System.out.println("Now you have " + this.count + " tasks in the list.");

        duke.DeleteLine(delete.toString());
    }

    public int getCount() {
        return this.count;
    }
}
