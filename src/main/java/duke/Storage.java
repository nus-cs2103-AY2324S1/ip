package duke;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Storage class handles the saving and loading of task data to and from a file.
 * It is responsible for converting tasks to a suitable format for storage and retrieving them when needed.
 */
public class Storage {
    /**
     * The directory path where the task data file is stored.
     */
    private final String dir;

    /**
     * Constructs a Storage object with the specified directory path.
     *
     * @param dir The directory path where the task data file is stored.
     */
    public Storage(String dir) {
        this.dir = dir;
    }

    /**
     * Saves the tasks from a TaskList to a file in the specified directory.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     */
    public void saveTasksToFile(TaskList tasks) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        try {
            File outputFile = new File(dir);
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
            fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter((fileWriter));
            for (Task t : tasks.getList()) {
                bufferedWriter.write(t.toSaveString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            UiGUI.Error(e);
        }
    }

    /**
     * Loads tasks from a file in the specified directory and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects representing the tasks loaded from the file.
     */
    public ArrayList<Task> loadSaveFile() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        ArrayList<Task> input = new ArrayList<Task>();
        try {
            File outputFile = new File(dir);
            outputFile.getParentFile().mkdirs();
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            fileReader = new FileReader(dir);
            bufferedReader = new BufferedReader((fileReader));
            String nextLine;
            nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                String[] splitted = nextLine.split(" \\| ");
                switch (splitted[0]) {
                case "T":
                    input.add(new ToDo(splitted[2], (splitted[1] == "1")));
                    break;
                case "E":
                    input.add(new Event(splitted[2], splitted[3], splitted[4], (splitted[1] == "1")));
                    break;
                case "D":
                    int key = Integer.valueOf(splitted[4]);
                    if (key == 2) {
                        input.add(new Deadline(splitted[2], LocalDateTime.parse(splitted[3]), (splitted[1] == "1")));
                    } else if (key == 1) {
                        input.add(new Deadline(splitted[2], LocalDate.parse(splitted[3]), (splitted[1] == "1")));
                    } else {
                        UiGUI.badDateLoaded();
                    }
                    break;
                }
                nextLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            UiGUI.Error(e);
        }
        return input;
    }
}
