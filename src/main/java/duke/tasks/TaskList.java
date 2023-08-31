package duke.tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(Scanner sc) {
        super();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Task task = Task.createTaskFromSavedData(line);
            this.add(task);
        }
    }

    public void addTask(Task task) {
        this.add(task);
    }

    public Task deleteTask(int taskNumber) {
        return this.remove(taskNumber - 1);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int length = 1; length < this.size() + 1; length += 1) {
            System.out.println(length + "." + this.get(length - 1));
        }
    }
}
