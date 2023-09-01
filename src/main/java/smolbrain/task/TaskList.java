package smolbrain.task;

import smolbrain.Storage;
import smolbrain.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public void addTask(Task task) {
        tasklist.add(task);
    }

    public void deleteTask(int id) {
        tasklist.remove(id);
    }

    public void markTask(int id) {
        tasklist.get(id).mark();
    }

    public void unmarkTask(int id) {
        tasklist.get(id).unmark();
    }

    public int getSize(){
        return tasklist.size();
    }

    public void displayTasks(Ui ui) {
        for (int i = 0; i < tasklist.size(); i++) {
            ui.showMessage((i + 1) + ". " + tasklist.get(i));
        }
    }

    public Task getTask(int id) {
        return tasklist.get(id);
    }

    public void updateTasks(Storage storage) {
        try {
            if (tasklist.size() == 0) {
                storage.writeToFile("");
            } else {
                for (int i = 0; i < tasklist.size(); i++) {
                    if (i == 0) {
                        storage.writeToFile(tasklist.get(i).encode());
                    } else {
                        storage.appendToFile(System.lineSeparator() + tasklist.get(i).encode());
                    }
                }
            }
        } catch (IOException e) {
            new Ui().showError(e);
        }
    }

    public void findTasks(String keyword, Ui ui) {
        if (tasklist.size() > 0) {
            for (int i = 0; i < tasklist.size(); i++) {
                if (tasklist.get(i).contain(keyword)) {
                    ui.showMessage((i + 1) + ". " + tasklist.get(i));
                }
            }
        }
    }

}
