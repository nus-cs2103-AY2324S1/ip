package functional;

import commands.Command;
import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The storage task encapsulates loading and saving of stored TaskList
 */
public class Storage {
    String filePath;

    /**
     * Constructs a Storage object with the specified filePath
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the task stored in the local drive
     * @return TaskList retrieved from the drive
     * @throws functional.DukeException
     */
    public List<Task> load() throws DukeException {
        File f = new File(filePath);
        TaskList<Task> tl = new TaskList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] input = line.split(" ", 2);
                Command c = Parser.parse(input[1]);
                Ui ui = new Ui(input[1]);
                c.execute(tl, ui, Boolean.parseBoolean(input[0]), true);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
        System.out.println("Opening Saved File");
        return tl;
    }

    /**
     * Saves the current TaskList to the drive
     * @param tl TaskList to be saved
     */
    public void save(TaskList<Task> tl) {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        }
        System.out.println("Saving file");
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tl) {
                fw.write(task.isMarked() + " " + task.getContent() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("File successfully Saved");
    }

}

