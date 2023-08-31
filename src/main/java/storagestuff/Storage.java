package storagestuff;

import duke.DukeException;
import duke.Parser;

import taskstuff.Task;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * A class which handles reading and writing from storage file.
 */
public class Storage {

    /**
     * String representing the filepath of storage file.
     */
    private final String filePath;

    /**
     * Initialises the filePath and makes the file and directory if they don't exist.
     *
     * @param filePath String representing filePath of storage file.
     * @Throws duke.DukeException Throws duke.DukeException if error occurred during accessing or making file.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
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
     * @return returns an array consisting of tasks loaded from the data file.
     * @throws DukeException Throws dukeException if error occurred during loading.
     */
    public ArrayList<Task>  load() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(this.filePath);
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
            System.out.println(e.getMessage());
            e.printStackTrace();
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
     * Stores the given strings in datafile.
     * @param s An array of strings representing the tasks to stores in data file.
     * @throws DukeException throws dukeException if error occurred during storing.
     */
    public void store(String[] s) throws DukeException {
        File file = new File(this.filePath);
        file.delete();
        file = new File(this.filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error in saving data. Data lost.");
        }
        BufferedWriter bufferedWriter  = null;
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < s.length; i++) {
                bufferedWriter.write(s[i]);
                bufferedWriter.newLine();
            }

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
