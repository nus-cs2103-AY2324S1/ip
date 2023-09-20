package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that retrieves and stores the list of tasks locally.
 */
public class Storage {

    private final String taskPath = "toothlessTasks.txt";
    private final String notePath = "toothlessNotes.txt";

    /**
     * Reads the previously saved list of tasks and returns it in a list of task descriptions.
     *
     * @return A list of task descriptions.
     */
    public List<String> readTaskFile() {
        List<String> output = new ArrayList<>();
        File f = new File(taskPath);
        try {
            f.createNewFile();
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String task = s.nextLine().trim();
                output.add(task);
            }

            return output;
        } catch (IOException e) {
            return output;
        }
    }

    /**
     * Reads the previously saved list of notes and returns it in a list of note descriptions.
     *
     * @return A list of note descriptions.
     */
    public List<String> readNoteFile() {
        List<String> output = new ArrayList<>();
        File f = new File(notePath);
        try {
            f.createNewFile();
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String note = s.nextLine().trim();
                output.add(note);
            }

            return output;
        } catch (IOException e) {
            return output;
        }
    }

    /**
     * Writes a string of tasks to a file.
     *
     * @param fw File to be written in.
     * @param tasks Tasks to be written.
     * @throws IOException If an I/O error occurs.
     */
    private void writeToFile(FileWriter fw, String tasks) throws IOException {
        fw.write(tasks);
        fw.close();
    }

    /**
     * Updates the local file with the new list of tasks.
     *
     * @param tasks List of task descriptions.
     * @return The status of whether the save was successful or not.
     */
    public String saveToDisk(List<String> tasks, List<String> notes) {
        try {
            FileWriter fwTasks = new FileWriter(taskPath);
            FileWriter fwNotes = new FileWriter(notePath);

            String inputTasks = "";
            for (int i = 0; i < tasks.size(); i++) {
                inputTasks = inputTasks + tasks.get(i) + "\n";
            }

            String inputNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                inputNotes = inputNotes + notes.get(i) + "\n";
            }

            writeToFile(fwTasks, inputTasks);
            writeToFile(fwNotes, inputNotes);
            return "Tasks saved successfully";
        } catch (IOException e) {
            return "Tasks could not be saved";
        }
    }

}
