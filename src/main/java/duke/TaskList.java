package duke;

import duke.Exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }


    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) {
        this.tasks.get(index).markAsNotDone();
    }


    public void printTasks() {
        try {
            System.out.println("\tHere are the tasks in your list:");
            if (tasks.size() == 0) {
                throw new DukeException("\t Seems like you have no tasks at the moment :) ");

            }
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("\t" + i + ". " + tasks.get(i - 1).toString());
            }
        }
        catch (DukeException e) {
            e.printMessage();
        }
    }









}
