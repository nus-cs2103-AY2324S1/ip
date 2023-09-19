package kiera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import kiera.exception.KieraException;
import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;
import kiera.task.Todo;

/**
 * Loads and saves tasks to a file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the storage file.
     * @throws KieraException If there is an error with loading tasks or finding the storage file.
     */
    public ArrayList<Task> load() throws KieraException {
        File f = new File(filePath);
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                Task t;
                String next = s.nextLine();
                String[] r = next.split(" // ");
                String type = r[0];
                String done = r[1];
                String desc = r[2];
                if (type.equals("T")) {
                    t = new Todo(desc);
                } else if (type.equals("D")) {
                    t = new Deadline(desc);
                } else {
                    t = new Event(desc);
                }
                if (done.equals("X")) {
                    t.markAsDone();
                } else {
                    t.markAsUndone();
                }
                result.add(t);

            }
        } catch (FileNotFoundException e) {
            throw new KieraException(e.getMessage());
        }
        return result;
    }

    /**
     * Saves current task list to the storage file.
     *
     * @param tasks List of tasks containing tasks to be saved.
     */
    public void save(TaskList tasks) {
        String text = tasks.getTasks().stream()
                .map(task -> task.toStorageString() + "\n")
                .collect(Collectors.joining());
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new KieraException("data not saved..." + e);
        }
    }

}
