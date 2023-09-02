package duke.tasks;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public void addTask(Task task) {
        this.add(task);
    }

    public void deleteTask(int index) {
        this.remove(index - 1);
        Task.taskCount--;
    }

    public void printTasks() {
        if (this.size() == 0) {
            System.out.println("Either you are a very productive person, or you have nothing to do.");
        } else {
            System.out.println("This is what you've been procrastinating about...");
            for (int i = 0; i < this.size(); i++) {
                System.out.println((i + 1) + ". " + this.get(i));
            }
        }
    }
}
