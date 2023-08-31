package anto;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Storage class handles all storing and loading of data.
 */
public class Storage {
    private Ui ui;
    private String relativePath;
    private Path absolutePath;
    private File antoFile;

    /**
     * Creates a Storage class.
     * @param ui Ui that handles all printing to the command line.
     * @param filePath Relative file path of file to load data from.
     */
    public Storage(Ui ui, String filePath) {
        this.ui = ui;
        this.relativePath = filePath;
        this.absolutePath = Paths.get(relativePath).toAbsolutePath();
        this.antoFile = absolutePath.toFile();

    }

    /**
     * Checks file at file path and loads data if it is there, else create a new file.
     *
     * @return ArrayList of Tasks representing data.
     * @throws AntoException Throws exception if there is an IO Exception.
     */
    public ArrayList<Task> loadSave() throws AntoException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();

            // If file doesn't exist
            if (!antoFile.exists()) {
                antoFile.createNewFile();
                this.ui.printNoSavedFile();
            } else {
                Scanner sc = new Scanner(antoFile);
                while (sc.hasNextLine()) {
                    String currLine = sc.nextLine();
                    String[] currLineArr = currLine.split(Pattern.quote(" | "));
                    if (currLineArr[0].equals("T")) {
                        Task newTask = new Todo(currLineArr[2]);
                        if (currLineArr[1].equals("1")) {
                            newTask.markAsDone();
                        }
                        taskList.add(newTask);
                    } else if (currLineArr[0].equals("D")) {
                        Task newTask = new Deadline(currLineArr[2], currLineArr[3]);
                        if (currLineArr[1].equals("1")) {
                            newTask.markAsDone();
                        }
                        taskList.add(newTask);
                    } else if (currLineArr[0].equals("E")) {
                        Task newTask = new Event(currLineArr[2], currLineArr[3], currLineArr[4]);
                        if (currLineArr[1].equals("1")) {
                            newTask.markAsDone();
                        }
                        taskList.add(newTask);
                    }
                }
                this.ui.printSavedFileFound(taskList);
            }
            return taskList;
        } catch (java.io.IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
    }
}
