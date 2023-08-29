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
    private File f = null;

    /**
     * Constructs a new Storage that deals with the file at the corresponding path.
     *
     * @param filepath Path to the file.
     */
    public Storage(String filepath) {
        this.f = new File(filepath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

    /**
     * Loads all the lines in the file and stores in a list.
     *
     * @return The list with all the lines.
     */
    public ArrayList<String> load() {
        Scanner sc;
        ArrayList<String> lines = new ArrayList<>();
        try {
            sc = new Scanner(f);
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            lines.add(s);
        }
        return lines;
    }

    /**
     * Stores the strings from a list to a file.
     *
     * @param strings The list of strings to be stored.
     */
    public void store(ArrayList<String> strings) {
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            for (String s : strings) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }
}
