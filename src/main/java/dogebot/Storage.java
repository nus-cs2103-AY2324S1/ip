package dogebot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

/**
 * Storage class handles reading and writing of tasks (i.e. reading from txt file, writing to txt file).
 *
 * @author Kenvyn Kwek
 */
public class Storage {
    private File file;

    /**
     * Initializes Storage object with input File object.
     *
     * @param file file to read from and write to.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Reads tasks from text file.
     *
     * @return Tasks from text file.
     */
    public TaskList readFromTxtFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                String[] sArray = s.split(" \\| ");

                boolean isDone = (sArray[1].equals("1")) ? true : false;
                String description = sArray[2];

                switch (sArray[0]) {
                case "T":
                    tasks.add(new ToDos(description, isDone));
                    break;
                case "D":
                    String by = sArray[3];
                    String reminder = (s.contains(" | Reminder: ")) ? s.split(" \\| Reminder: ")[1] : "";
                    tasks.add(new Deadline(description, by, isDone, reminder));
                    break;
                case "E":
                    String[] duration = sArray[3].split(" - ");
                    String start = duration[0];
                    String end = duration[1];
                    String eventReminder = (s.contains(" | Reminder: ")) ? s.split(" \\| Reminder: ")[1] : "";
                    tasks.add(new Event(description, start, end, isDone, eventReminder));
                    break;
                default:
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return new TaskList(tasks);
    }

    /**
     * Saves current tasks to text file.
     *
     * @param tasks Current tasks.
     */
    public void save(TaskList tasks) { // overwrite 'tasklist.txt' with 'tasks' arraylist
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task task : tasks.getTasks()) {
                bw.write(task.toString() + "\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
