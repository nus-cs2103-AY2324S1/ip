package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * The Storage class that encapsulates the filepath and the file.
 */
public class Storage {
    private final String filePath;
    private File file;

    /**
     * Instantiates a new Storage.
     *
     * @param filepath the filepath
     */
    Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads file and handles the case if the file does not exist or already exists.
     *
     * @return the file
     */
    public File load() {
        File openfile = new File(filePath);
        try {
            if (openfile.createNewFile()) {
                System.out.println("New file created" + openfile.getName());
            } else {
                System.out.println("File loaded.");
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("An error occurred.");
        }
        file = openfile;
        return openfile;
    }


    /**
     * Updates the file given the updated TaskList list.
     *
     * @param list the list
     */
    public void updateFile(ArrayList<Task> list) {
        assert file.canWrite() : "File writing is supposed to return true";
        try (FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(list.get(list.size() - 1).dataString());
        } catch (IOException e) {
            System.out.println("nothing");
        }
    }
}
