package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * The storage class reads and writes from
 * the hard drive.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from the hard drive and returns the
     * tasks that have been read.
     *
     * @return An ArrayList of the tasks on hard drive.
     * @throws FileNotFoundException If file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] components = line.split(" \\| ");
            Task t;
            if (components[0].startsWith("T")) {
                t = new Todo(components[1], components[2]);
            } else if (components[0].startsWith("D")) {
                t = new Deadline(components[1], components[2], LocalDate.parse(components[3]));
            } else {
                t = new Event(components[1], components[2], components[3], components[4]);
            }
            tasks.add(t);
        }
        return tasks;
    }

    /**
     * Rewrites the file in hard drive.
     *
     * @param tasks TaskList containing the tasks.
     * @throws IOException If file is corrupted.
     */
    public void rewrite(TaskList tasks) throws IOException {
        ArrayList<Task> arrayTasks = tasks.getTasks();
        FileWriter file = new FileWriter("storage.txt");
        for (int i = 0; i < arrayTasks.size(); i++) {
            Task task = arrayTasks.get(i);
            file.write(task.fileRepresentation());
        }
        file.close();
    }

    /**
     * Adds a new task to the hard drive.
     *
     * @param task the task to be added.
     * @throws IOException If the file is corrupted.
     */
    public void append(Task task) throws IOException {
        FileWriter file = new FileWriter("storage.txt", true);
        file.write(task.fileRepresentation());
        file.close();
    }
}
