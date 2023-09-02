import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

    private ArrayList<Task> storagePile;

    public TaskList() {
        storagePile = Storage.loadTasks();
    }

    public String toString() {
        int leng = storagePile.size();
        String listed = "";
        for (int i = 1; i <= leng; i++) {
            listed += String.format("%s - %s",
                    i, storagePile.get(i-1)) +" \n" ;
        }
        return listed;
    }

    public void checkItem(int x) {
        storagePile.get(x-1).markDone();
    }

    public void notDoneItem(int x) {
        storagePile.get(x-1).markUndone();
    }

    public void deleteIndex(int x) {
        storagePile.remove(x-1);
    }

    public Task getTask(int x) { return storagePile.get(x-1); }

    public int numOfItems() {
        return storagePile.size();
    }

    public void input(String item) throws InvalidInput, IncompleteInput  {
        String firstWord = item.split(" ")[0];

        if (item.split(" ").length == 1) {
            if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                throw new IncompleteInput("Not finished");
            } else {
                throw new InvalidInput("Invalid");
            }
        } else if (firstWord.equals("todo")) {
            String task = item.split(" ", 2)[1];
            storagePile.add(new ToDoTask(task));
        } else if (firstWord.equals("deadline")) {
            String task = item.split(" ", 2)[1];
            storagePile.add(new DeadlineTask(task));
        } else {
            String task = item.split(" ", 2)[1];
            storagePile.add(new EventTask(task));
        }
    }

    public void saveToFile(PrintWriter pw) {
        for (Task item : storagePile) {
            String str = item.toString();
            pw.println(str);
        }
    }

}
