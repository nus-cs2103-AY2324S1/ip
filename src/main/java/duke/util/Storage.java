package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.NoFileException;
/**
 * Storage class to help with reading and writing of data from files.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs an event with a given description. Completion of the task
     * is false by default.
     * @param filePath The location of the file to be read.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from file into an ArrayList to be further processed.
     * @return ArrayList of string containing the information read from the file
     * @throws NoFileException throws an error if no such file is found with the given filepath
     */
    public ArrayList<String> load() throws NoFileException {
        File f = new File(filePath);
        ArrayList<String> fileData = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String info = fileReader.nextLine();
                fileData.add(info);
            }
            fileReader.close();
            return fileData;
        } catch (FileNotFoundException e) {
            throw new NoFileException(e.getMessage());
        }
    }

    /**
     * Writes the current tasklist back into a file.
     * @param taskList ArrayList of strings to be written into the file
     * @throws NoFileException Exception when file to be written in cannot be found
     */
    public void rewriteFile(TaskList taskList) throws NoFileException {
        try {
            ArrayList<String> tasks = taskList.toWriteFormat();
            FileWriter fw = new FileWriter(this.filePath);
            for (String task : tasks) {
                assert task != null : "adding null task";
                fw.append(task);
                fw.append(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new NoFileException(e.getMessage());
        }
    }
}
