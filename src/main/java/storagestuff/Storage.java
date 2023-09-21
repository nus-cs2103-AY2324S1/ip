package storagestuff;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Parser;
import taskstuff.Task;





/**
 * A class which handles reading and writing from storage file.
 */
public class Storage {

    /**
     * String representing the filepath of storage file.
     */
    private final String filepath;

    /**
     * Initialises the filePath and makes the file and directory if they don't exist.
     *
     * @param filePath String representing filePath of storage file.
     * @throws DukeException If error occurred during accessing or making file.
     */
    public Storage(String filePath) throws DukeException {
        this.filepath = filePath;
        try {

            File file = new File(filePath);
            File directory = new File(file.getParentFile().getAbsolutePath());
            directory.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error in accessing or creating data file. Try again.");
        }
    }


    /**
     * Returns an array of tasks that are loaded from the data file.
     *
     * @return An array consisting of tasks loaded from the data file.
     * @throws DukeException If error occurred during loading.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(this.filepath);
            bufferedReader = new BufferedReader(fileReader);
            String dataInput;

            while ((dataInput = bufferedReader.readLine()) != null) {
                try {
                    Task task = Parser.parseData(dataInput);
                    tasks.add(task);
                } catch (DukeException e) {
                    throw new DukeException("Data file is corrupted. Delete data file and try again.");
                }

            }
        } catch (IOException e) {
            throw new DukeException("An error occurred when loading data. Try again.");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new DukeException("An exception occurred when closing file reader.");
                }
            }
        }
        return tasks;
    }

    /**
     * Stores the given string in datafile.
     *
     * @param s The String representing the tasks to stores in data file.
     * @throws DukeException If error occurred during storing.
     */
    public void store(String s) throws DukeException {
        File file = new File(this.filepath);
        file.delete();
        file = new File(this.filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error in saving data. Data lost.");
        }
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(this.filepath);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(s);


        } catch (IOException e) {
            throw new DukeException("Error in saving data. Data lost.");
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new DukeException("An error occurred when closing file writer.");
                }
            }
        }


    }


}
