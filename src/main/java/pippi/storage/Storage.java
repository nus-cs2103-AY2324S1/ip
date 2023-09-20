package pippi.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pippi.parser.DateFormatter;
import pippi.task.Deadline;
import pippi.task.Event;
import pippi.task.Task;
import pippi.task.ToDo;

/**
 * The Storage class supports methods for reading tasks from storage
 * and storing tasks into storage.
 *
 * @author Nathan
 */
public class Storage {
    private static final String FILE_PATH = "./data/Pippi.txt";

    /**
     * Returns an array list of tasks stored in the Pippi.txt file
     * (array is empty if file is empty or not found)
     * @return Array List of tasks
     */
    public static ArrayList<Task> readTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // FileReader will open that file from that
            // directory, if there is no file found it will
            // throw an IOException
            FileReader fr = new FileReader(FILE_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length == 0) {
                    continue;
                }
                String type = parts[0];
                String status = parts[1];
                switch (type) {
                case "T":
                    ToDo t = new ToDo(parts[2]);
                    if (status.equals("1")) {
                        t.mark();
                    }
                    tasks.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(parts[2],
                            DateFormatter.convertStringToLocalDate(parts[3].trim()));
                    if (status.equals("1")) {
                        d.mark();
                    }
                    tasks.add(d);
                    break;
                case "E":
                    String start = parts[3].split("to ")[0];
                    String end = parts[3].split("to ")[1];
                    Event e = new Event(parts[2], start, end);
                    if (status.equals("1")) {
                        e.mark();
                    }
                    tasks.add(e);
                    break;
                default:
                    System.out.println("Data read is in wrong format");
                }
            }
        } catch (IOException e) {
            System.out.println("No file found exception");
        }
        return tasks;
    }

    /**
     * Updates the memory when a task is modified or added
     * @param tasks (an array list of tasks after updated)
     */
    public static void updateTask(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            String all = "";
            for (Task curr : tasks) {
                all = all + curr.toMemory() + "\n";
            }
            fw.write(all);
            fw.close();
        } catch (IOException e) {
            System.out.println("File input/output not found exception");
        }
    }

}
