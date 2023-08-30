import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
public class Tasks {

    private ArrayList<Task> storagePile;

    public Tasks() {
        storagePile = Tasks.loadTasks();
    }

    public void input(String item) throws InvalidInput, IncompleteInput  {
        String firstWord = item.split(" ")[0];

        if (item.split(" ").length == 1) {
            if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                throw new IncompleteInput("X");
            } else {
                throw new InvalidInput("X");
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

    private static final String path = "data/";

    public void saveTasks() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(path));

            for (Task item : storagePile) {
                String str = item.toString();
                writer.println(str);
            }
        } catch (IOException e) {

        }
    };

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            System.out.println(line);
            while (line != null) {
                String[] splitsy = line.split(" | ");
                tasks.add(new Task(splitsy[0], splitsy[1], splitsy[2], splitsy[3]));
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return tasks;
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

    public String showThisTask(int x) {
        return storagePile.get(x-1).toString();
    }

    public int numOfItems() {
        return storagePile.size();
    }
}
