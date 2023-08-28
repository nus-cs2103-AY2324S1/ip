import java.util.ArrayList;

public class TaskList {
    // change implementation to collections
    private ArrayList<Task> tasks;
    private Storage db;

    public TaskList() {
        db = new Storage("data/duke.txt");
        this.tasks = db.loadTask();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        db.saveTask(tasks);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }
        Task deletedTask = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        db.saveTask(tasks);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i).toString());
        }
    }

    public void markTaskAsDone(int taskNumber) throws DukeException{
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }

        this.tasks.get(taskNumber - 1).markAsDone();
        db.saveTask(tasks);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(taskNumber - 1).toString());
    }

    public void markTaskAsUndone(int taskNumber) throws DukeException{
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }
        this.tasks.get(taskNumber - 1).markAsUndone();
        db.saveTask(tasks);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(taskNumber - 1).toString());
    }
}
