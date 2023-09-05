package Duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Duke.Tasks.*;
import Duke.Exceptions.*;

public class Storage {

    private static final String path = "./data/data.txt";
    public static void saveTasks(TaskList x) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            x.saveToFile(writer);

            writer.close();

        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {

            File file = new File(path);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                List<String> splitsy = new ArrayList<>(Arrays.asList(line.split("\\| ")));
                if (splitsy.size() < 4) {
                    splitsy.add("hi");
                }
                tasks.add(Storage.correctTask(splitsy.get(0), splitsy.get(1), splitsy.get(2), splitsy.get(3)));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File");
            e.printStackTrace();
            Storage.createEmptyFile();
        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        }
        return tasks;
    }


    public static void createEmptyFile() {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println("New file created at path: " + path);
        } catch (IOException e) {
            System.out.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Task correctTask(String type, String completion, String item, String deadline) {
        if (type.equals("[T] ")) {
            Task t = new ToDoTask(item);

            if (completion.equals("[X] ")) {
                t.markDone();
            }

            return t;
        } else if (type.equals("[E] ")) {
            String from = deadline.split("-")[0];
            String to = deadline.split("-")[1];
            Task t = new EventTask(item.trim() + " /from " + from.trim() + " /to " + to.trim());

            if (completion.equals("[X] ")) {
                t.markDone();
            }

            return t;
        } else {
            Task t = new DeadlineTask(item.trim() + " /by " + deadline.trim());

            if (completion.equals("[X] ")) {
                t.markDone();
            }

            return t;
        }
    }

}

