package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void addTask(Task t) {
        this.taskList.add(t);
        Ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " in the list.");
        Ui.showLine();

    }
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public void deleteTask(int i) {
        Task t = this.taskList.get(i);
        this.taskList.remove(i);
        int length = this.taskList.size();
        Ui.showLine();
        System.out.println("Noted! I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + length + " tasks in the list.");
        Ui.showLine();
    }

    public void printTasks() {
        int len =this.taskList.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < len; i++) {
            int task_number = i + 1;
            Task t = this.taskList.get(i);
            System.out.println(task_number
                    + ". "
                    + t);
        }
    }
}
