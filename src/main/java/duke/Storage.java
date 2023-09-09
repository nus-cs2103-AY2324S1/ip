package duke;

import duke.tasks.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class that encapsulates the filepath and the file.
 */
public class Storage {
    final private String FILEPATH;
    private File file;

    /**
     * Instantiates a new Storage.
     *
     * @param filepath the filepath
     */
    Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * Loads file and handles the case if the file does not exist or already exists.
     *
     * @return the file
     */
    public File load() {
        File openfile = new File(FILEPATH);
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
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(list.get(list.size() - 1).dataString());
        } catch (IOException e) {
            System.out.println("nothing");
        }
    }

    /**
     * Returns the file.
     *
     * @return the file
     */
    public File openfile() {
        return file;
    }
}
