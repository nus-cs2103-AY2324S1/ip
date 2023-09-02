package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the loading and saving of data for the Duke application.
 * This class handles reading and writing data from/to a text file.
 */
public class Storage {
    private String folder;
    private String text;

    /**
     * Constructs a Storage object with the specified folder and text file name.
     *
     * @param folder The folder where the data file is located or should be created.
     * @param text   The name of the text file to be used for data storage.
     */
    public Storage(String folder, String text) {
        this.folder = folder;
        this.text = text;
    }

    /**
     * Loads data from the specified text file and returns it as a list of strings.
     *
     * @return An ArrayList of strings containing data read from the text file.
     * @throws DukeException If an error occurs while loading the data.
     */
    public ArrayList<String> loadFile() throws DukeException {
        ArrayList<String> list = new ArrayList<>();
        try {
            File directory = new File(folder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, text);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                list.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        } catch (IOException e) {
            throw new DukeException("IOException occurred.");
        } catch (Exception e) {
            throw new DukeException("An error occurred.");

        }
        return list;
    }

    /**
     * Saves data from the given TaskList to the text file.
     *
     * @param taskList The TaskList containing data to be saved.
     * @throws DukeException If an error occurs while saving the data.
     */
    public void saveFile(TaskList taskList) throws DukeException {
        File directory = new File(folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, text);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            taskList.save(writer);
        } catch (Exception e) {
            throw new DukeException("An error occurred with saving.");
        }
    }
}
