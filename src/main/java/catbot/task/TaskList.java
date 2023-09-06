package catbot.task;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {

    private ArrayList<Task> tasks;
    private final String path;

    public TaskList(String path) {
        this.path = path;
        this.tasks = new ArrayList<>();
        readSerializedFromFile();
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
        public final int lowerBound, upperBound;

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
        writeSerializedToFile();
    }

    //region FileIO

    private void writeSerializedToFile() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path));
            output.writeObject(this.tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readSerializedFromFile() {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(path));
            Object readObject = input.readObject();
            //noinspection unchecked
            tasks = (ArrayList<Task>) readObject;
            input.close();
        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) { //save corrupted
            throw new RuntimeException(e);
        }
    }

    //endregion

}
