package duke;

import duke.exception.DukeFileNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An object that deals with loading and saving tasks in the file.
 */
public class Storage {
    /** The file that the storage will be dealing with. */
    private File taskFile;

    /**
     * Constructs a new Storage that deals with the file at the corresponding path.
     *
     * @param filepath Path to the file.
     */
    public Storage(String filepath) {
        try {
            taskFile = new File(filepath);
            taskFile.createNewFile();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

    /**
     * Loads all the lines in the file and stores in a list.
     *
     * @return The list with all the lines.
     */
    public ArrayList<String> loadFromFile() {
        Scanner sc;
        ArrayList<String> linesInFile = new ArrayList<>();
        try {
            sc = new Scanner(taskFile);
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            linesInFile.add(s);
        }
        return linesInFile;
    }

    /**
     * Stores the strings from a list to a file.
     *
     * @param strings The list of strings to be stored.
     */
    public void storeToFile(ArrayList<String> strings) {
        FileWriter fw;
        try {
            fw = new FileWriter(taskFile);
            for (String s : strings) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }
}
