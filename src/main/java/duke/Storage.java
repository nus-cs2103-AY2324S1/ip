package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Task;


/**
 * Class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private List<Task> list;

    /**
     * Constructor for storage object. Initialises the filePath and list.
     *
     * @param filePath The filePath of the file to read from and write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    /**
     * Reads the file and adds all the tasks from the file into the list.
     *
     * @return The list containing tasks read from the file
     */
    public List<Task> load() {
        try {
            // create data directory if it does not exist
            Files.createDirectories(Paths.get("./data"));
            File f = new File(filePath);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task task = Parser.stringToTask(s.nextLine());
                this.list.add(task);
            }
            return this.list;

        } catch (IOException e) {
            System.out.println("Something went wrong reading the file.");
        } catch (DukeException e) {
            System.out.println(e);
        }

        // returns empty list if file is corrupted
        return this.list;

    }

    /**
     * Writes all the tasks in the current list to the file.
     *
     * @throws DukeException If some error occurs with the writing process.
     */
    public void writeToFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : list) {
                fw.write(task.tasktoString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong writing to the file.");
        }

    }


}
