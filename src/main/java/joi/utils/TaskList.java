package joi.utils;

import java.util.ArrayList;

import joi.ui.Ui;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void listTasks(Ui ui) {
        ui.printToScreen("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            ui.printToScreen((i+1) + ". " + this.taskList.get(i));
        }
        ui.printToScreen("");
    }

    public void markAsDone(int taskIdx) {
        if (taskIdx < taskList.size()) {
            this.taskList.get(taskIdx).setDone();
        }
    }
    public void markAsDoneVerbose(int taskIdx, Ui ui) {
        markAsDone(taskIdx);
        ui.printToScreen("Nice! I've marked this task as done:");
        ui.printToScreen(this.taskList.get(taskIdx) + "\n");
    }

    public void unmarkAsDone(int taskIdx) {
        if (taskIdx < this.taskList.size()) {
            this.taskList.get(taskIdx).setNotDone();
        }
    }
    public void unmarkAsDoneVerbose(int taskIdx, Ui ui) {
        unmarkAsDone(taskIdx);

        ui.printToScreen("OK, I've marked this task as not done yet:");
        ui.printToScreen(this.taskList.get(taskIdx) + "\n");
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public void addTaskVerbose(Task newTask) {
        addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.\n");
    }

    public void deleteTaskVerbose(int taskIdx) throws InvalidCommandException {
        if (taskIdx >= this.taskList.size()) {
            throw new InvalidCommandException("Delete index is out of range.");
        }
        Task tmpTask = this.taskList.get(taskIdx);

        System.out.println("Noted. I've removed this task:");
        System.out.println(tmpTask);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.\n");

        this.taskList.remove(taskIdx);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int idx) {
        return this.taskList.get(idx);
    }
}
