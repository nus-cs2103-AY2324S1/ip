package functions;

import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for loading and saving task data from/to a file.
 */
public class Storage {

    /**
     * The filepath of the data file.
     */
    protected String filepath;

    /**
     * The File object representing the data file.
     */
    protected File file;

    /**
     * Constructs a Storage object with the given filepath.
     *
     * @param filepath The filepath of the data file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    /**
     * Loads task data from the data file.
     *
     * @return An ArrayList of task data read from the file.
     * @throws FileNotFoundException If the data file is not found.
     */
    public ArrayList<String> loadFiles() throws FileNotFoundException {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while(s.hasNext()) {
            tasks.add(s.nextLine());
        }
        return tasks;
    }

    /**
     * Writes text to a file.
     *
     * @param filePath  The filepath of the file to write to.
     * @param textToAdd The text to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves task data to the data file.
     *
     * @param tasks An ArrayList of Task objects to be saved.
     * @throws IOException If an I/O error occurs while saving the data.
     */
    public void saveFiles(ArrayList<Task> tasks) throws IOException {
        int i = tasks.size();
        String taskList = "";
        for (int j = 0; j < i; j++) {
            if (j == i - 1) {
                taskList += tasks.get(j).toString();
            } else {
                taskList += tasks.get(j).toString() + System.lineSeparator();
            }
        }
        writeToFile(this.filepath, taskList);
    }
}