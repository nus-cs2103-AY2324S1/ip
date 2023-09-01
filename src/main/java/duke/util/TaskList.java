package duke.util;

import duke.task.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        // arr is given from the duke.util.Storage.load()
        this.arr = arr;
    }

    public void add(Task instance) {
        this.arr.add(instance);
    }

    public Task delete(int num) {
        Task task = this.arr.get(num);
        this.arr.remove(num);
        return task;
    }

    public Task mark(int num) {
        return arr.get(num).description(true);
    }

    public Task unmark(int num){
        return arr.get(num).description(false);
    }

    public int size() {
        return this.arr.size();
    }

    @Override
    public String toString() {
        String toBePrinted = "";
        for (int i = 0; i < arr.size(); i++) {
            toBePrinted = toBePrinted.concat(arr.get(i).toString());
            toBePrinted += System.lineSeparator();
        }
        return toBePrinted;
    }


    public String toWrite() {
        String toBeWritten = "";
        for (int i = 0; i < arr.size(); i++) {
            toBeWritten = toBeWritten.concat(arr.get(i).toWrite());
            toBeWritten += System.lineSeparator();
        }
        return toBeWritten;
    }

}
