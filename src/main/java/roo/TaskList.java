package roo;

import roo.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> data = new ArrayList<>();
    private final Storage storage;

    public TaskList (Storage storage) {
        this.storage = storage;
    }

    public boolean isDone(int i) {
        return data.get(i).done();
    }

    public int size() {
        return this.data.size();
    }

    public void add(Task task) {
        this.data.add(task);
    }

    public void addNew(Task task) {
        this.data.add(task);
        this.storage.add(task);
    }

    public void clear() {
        this.data.clear();
        this.storage.clear();
    }

    public void listDateEvents(LocalDate date) {
        for (Task dt : data) {
            if (dt.getDate() != null && dt.getDate().getDayOfMonth() == date.getDayOfMonth()) {
                System.out.println("- " + dt.toString());
            }
        }
        System.out.println(" ");
    }

    public void markDone(int i) {
        data.get(i).markDone();
        storage.modifyFile(this.data);
    }

    public void markUndone(int i) {
        data.get(i).markUndone();
        storage.modifyFile(this.data);
    }

    public void delete(int i) {
        data.remove(i);
        storage.modifyFile(this.data);
    }

    public String taskDetails(int i) {
        return data.get(i).toString();
    }

    public void list() {
        if (data.isEmpty()) {
            System.out.println("Congrats!!! Nothing to do now!!!\n");
        } else {
            for (int i = 0; i < data.size(); i++) {
                Task dt = data.get(i);
                System.out.println((i + 1) + ". " + dt.toString());
            }
            System.out.println(" ");
        }
    }

}
