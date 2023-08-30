package anto;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private Ui ui;
    private String relativePath;
    private Path absolutePath;
    private File antoFile;

    public Storage(Ui ui, String filePath) {
        this.ui = ui;
        this.relativePath = filePath;
        this.absolutePath = Paths.get(relativePath).toAbsolutePath();
        this.antoFile = absolutePath.toFile();

    }

    public ArrayList<Task> loadSave() throws DukeException {
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
            throw new DukeException("☹ OOPS!!! IOException");
        }
    }
}
