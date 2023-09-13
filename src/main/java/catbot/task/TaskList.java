package catbot.task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TaskList {

    private ArrayList<Task> tasks;
    private final TaskArrayListStorage storage;

    public TaskList(String path) {
        if (path != null) {
            this.storage = new TaskArrayListStorage(path);
            this.tasks = storage.readSerializedFromFile();
        } else {
            this.storage = null;
            this.tasks = new ArrayList<>();
        }
    }


    public void addTask(Task task) {
        tasks.add(task);
        update();
    }

    public Task removeTask(int index) {
        Task removed = tasks.remove(index);
        update();
        return removed;
    }

    public void ifValidIndexElse(int index, Consumer<Integer> ifValid, Consumer<Integer> otherwise) {
        Bounds bounds = getIndexBounds();
        if (bounds.contains(index)) {
            ifValid.accept(index);
        } else {
            otherwise.accept(index);
        }
    }

    public Bounds getIndexBounds() {
        return new Bounds(1, tasks.size());
    }

    public static class Bounds {
        public final int lowerBound;
        public final int upperBound;

        public Bounds(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public boolean contains(int i) {
            return this.lowerBound <= i && i <= this.upperBound;
        }
    }

    public void markTask(int index) {
        tasks.get(index).setDone();
        update();
    }

    public void unmarkTask(int index) {
        tasks.get(index).setUndone();
        update();
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() { //todo clean up and also clear up every returned reference for tasks
        return tasks;
    }

    private void update() {
        this.storage.writeSerializedToFile(this.tasks);
    }

    public TaskList find(String string) {
        TaskList taskList = new TaskList(null);
        taskList.tasks = new ArrayList<>(
                tasks.stream().filter(task -> task.getDescription().contains(string)).collect(Collectors.toList())
        );
        return taskList;
    }

}
