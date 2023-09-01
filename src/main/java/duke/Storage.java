package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage system for Duke program.
 */
public class Storage {
    /** Default file path. */
    private static final String FILE_PATH = "../../duke.txt";
    /** The file to parse */
    private File file;

    /**
     * Constructs a Storage object with a File object.
     *
     * @param file The File object representing the file to be used for storage.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Constructs a Storage object with a string representing the path of the file.
     * If the file does not exist, it attempts to create it.
     * If the file cannot be created, it throws a DukeException.
     *
     * @param pathName The path of the file to be used for storage.
     * @throws DukeException If the file cannot be created.
     */
    public Storage(String pathName) throws DukeException {
        this.file = new File(pathName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to find or create file.");
        }
    }

    /**
     * Creates a Storage object using the default file path.
     * If the file does not exist, it attempts to create it.
     * If the file cannot be created, it throws a DukeException.
     *
     * @throws DukeException If the file cannot be created.
     */
    public Storage() throws DukeException {
        this.file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to find or create file.");
        }
    }

    /**
     * Reads the lines from the storage file and returns them as an array of strings.
     * If the storage file cannot be found, it throws a DukeException.
     *
     * @return An array of strings representing the lines in the storage file.
     * @throws DukeException If the storage file cannot be found.
     */
    public String[] readLines() throws DukeException {
        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }

        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNext()) {
            lines.add(sc.nextLine());
        }
        sc.close();
        return lines.toArray(new String[]{});
    }

    /**
     * Takes a string and writes it to the storage file.
     *
     * @param s The string to be written to the storage file.
     */
    public void write(String s) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.write(s);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to duke.txt");
        }
    }
}
