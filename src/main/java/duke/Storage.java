package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Writes and reads from the bot's save files.
 */
public class Storage {
    /** Contains path to save file **/
    private String filePath;

    /**
     * Constructor for Storage.
     * @param filePath path to save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data of tasks from save file.
     * @return ArrayList containing all tasks in a String array
     * @throws DukeException for errors that occur while reading from save file
     */
    public ArrayList<String[]> loadData() throws DukeException {
        ArrayList<String[]> stringList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(this.filePath));
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split("\\|");
                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].strip();
                }
                stringList.add(line);
            }
            sc.close();
            return stringList;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from data file.");
        }
    }

    /**
     * Writes list of tasks into a save file.
     * @param inputList tasks to be written to file
     * @throws DukeException for errors that occur while writing to save file
     */
    public void writeData(String[] inputList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (String t: inputList) {
                fw.write(t.strip()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing tasks to file.");
        }
    }
}
