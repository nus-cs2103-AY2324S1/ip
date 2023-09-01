package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import duke.parser.TaskParser;
import duke.task.Task;

/**
 * Handles saving and loading from local storage.
 * @author Toh Li Yuan (A0255811H)
 */
public class SaveData {
    private static final String SAVE_FILE_LOCATION = "./SaveFile.txt";

    /**
     * Saves the formatted data to local storage.
     *
     * @param taskData the formatted string of the data to be saved.
     */
    public static void saveData(String taskData) {
        File f = new File(SAVE_FILE_LOCATION);
        try {
            if (f.createNewFile()) {
                FileWriter fw = new FileWriter(SAVE_FILE_LOCATION);
                fw.write(taskData);
                fw.close();
            } else {
                if (f.delete()) {
                    FileWriter fw = new FileWriter(SAVE_FILE_LOCATION);
                    fw.write(taskData);
                    fw.close();
                } else {
                    throw new Exception("The file cannot be deleted!");
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * Loads the data from the local storage.
     *
     * @return An ArrayList of the loaded data. Returns an empty ArrayList if no stored data is found.
     */
    public static ArrayList<Task> loadData() {
        File f = new File(SAVE_FILE_LOCATION);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Optional<Task> optionalTask = TaskParser.parseSave(sc.nextLine());
                if (optionalTask.isPresent()) {
                    optionalTask.ifPresent(tasks::add);
                }
            }
            sc.close();
        } catch (Exception e) {

        }
        return tasks;
    }

}
