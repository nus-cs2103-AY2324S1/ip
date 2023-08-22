package joi.utils;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i+1) + ". " + this.taskList.get(i));
        }
        System.out.println();
    }

    public void markAsDone(int taskIdx) {
        if (taskIdx < taskList.size()) {
            this.taskList.get(taskIdx).setDone();
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(taskIdx) + "\n");
    }

    public void unmarkAsDone(int taskIdx) {
        if (taskIdx < this.taskList.size()) {
            this.taskList.get(taskIdx).setNotDone();
        }

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.taskList.get(taskIdx) + "\n");
    }

    public void addTask(Task newTask) {
        this.taskList.add((newTask));

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.\n");
    }
}
