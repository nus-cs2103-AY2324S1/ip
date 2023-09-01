import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
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

    private static final String path = "./data/data.txt";

    public void saveTasks() {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            for (Task item : storagePile) {
                String str = item.toString();
                writer.println(str);
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        }
    };

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            //System.out.println("What is pulled when I load");
            while (line != null) {
                //System.out.println(line);
                List<String> splitsy = new ArrayList<>(Arrays.asList(line.split("\\| ")));
                if (splitsy.size() < 4) {
                    splitsy.add("hi");
                }
                tasks.add(Task.correctTask(splitsy.get(0), splitsy.get(1), splitsy.get(2), splitsy.get(3)));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
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
