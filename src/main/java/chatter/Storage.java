package chatter;

import chatter.task.Deadline;
import chatter.task.Event;
import chatter.task.Task;
import chatter.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage that stores the list of tasks locally
 */
public class Storage {
    /** List of tasks read from the local data file */
    private ArrayList<Task> tasks = new ArrayList<Task>();
    /** File path for the local data file */
    private String path;

    /**
     * Constructor for chatter.Storage class.
     *
     * @param path File path of the local data file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Creates new directory and file for data storage if they do not exist. Reads
     * data from the local file.
     *
     * @return ArrayList of chatter.task.Task objects.
     * @throws IOException Error thrown is file cannot be created.
     */
    public ArrayList<Task> readFile() throws IOException {
        Path p = Paths.get(this.path);
        Path path = Paths.get(p.getParent().toString());
        Files.createDirectories(path);
        File f = new File(p.toString());
        f.createNewFile();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] input = s.nextLine().split(", ");
            Task t;

            switch (input[0]) {
            case("D"):
                t = new Deadline(input[2], input[3]);
                break;
            case("T"):
                t = new ToDo(input[2]);
                break;
            default:
                t = new Event(input[2], input[3], input[4]);
                break;
            }

            if (Boolean.parseBoolean(input[1])) {
                t.markAsDone();
            }

            tasks.add(t);
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the local data file in the appropriate format.
     *
     * @param list ArrayList of chatter.task.Task objects to be saved to the local data file.
     */
    public void saveFile(String storageString) {
        try {
            FileWriter fw = new FileWriter("./data/chatter.txt");
            fw.write(storageString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Got error");
        }
    }
}
