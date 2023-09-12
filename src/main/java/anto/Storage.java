package anto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
     *
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
            // If file doesn't exist
            ArrayList<Task> taskList;
            if (!antoFile.exists()) {
                antoFile.createNewFile();
                taskList = new ArrayList<>();
                this.ui.printNoSavedFile();
            } else {
                taskList = this.loadFileIntoArrayList();
                this.ui.printSavedFileFound(taskList);
            }
            return taskList;
        } catch (java.io.IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
    }

    private ArrayList<Task> loadFileIntoArrayList() throws FileNotFoundException, AntoException {
        assert antoFile != null;
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(antoFile);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            String[] currLineArr = currLine.split(Pattern.quote(" | "));

            Task newTask;
            switch (currLineArr[0]) {
            case "T":
                newTask = new Todo(currLineArr[2]);
                break;
            case "D":
                newTask = new Deadline(currLineArr[2], currLineArr[3]);
                break;
            case "E":
                newTask = new Event(currLineArr[2], currLineArr[3], currLineArr[4]);
                break;
            default:
                throw new AntoException("OOPS!!! File text is in wrong format");
            }

            if (currLineArr[1].equals("1")) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
        }
        return taskList;
    }

    /**
     * Adds task to Anto file.
     *
     * @param task Task to be added.
     * @throws AntoException Throws AntoException when there is an IOException.
     */
    public void addTaskToStorage(Task task) throws AntoException {
        try {
            FileWriter writer = new FileWriter(antoFile, true);
            if (task instanceof Todo) {
                // It is safe to type cast because the type is checked before
                Todo todo = (Todo) task;
                writer.write(todo.getStoreFormat());
            } else if (task instanceof Deadline) {
                // It is safe to type cast because the type is checked before
                Deadline deadline = (Deadline) task;
                writer.write(deadline.getStoreFormat());
            } else if (task instanceof Event) {
                // It is safe to type cast because the type is checked before
                Event event = (Event) task;
                writer.write(event.getStoreFormat());
            }
            writer.close();
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
    }

    /**
     * Mark task as done in storage file.
     *
     * @param index Index of task to mark.
     * @throws AntoException Throws AntoException if there is an IOException
     */
    public void markTaskAsDone(int index) throws AntoException {
        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 0 |", "| 1 |")
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
    }

    /**
     * Unmark task in storage file.
     *
     * @param index Index of task to unmark.
     * @throws AntoException Throws AntoException if there is an IOException.
     */
    public void unmarkTask(int index) throws AntoException {
        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 1 |", "| 0 |")
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
    }

    /**
     * Delete task in storage file.
     *
     * @param index Index of task to delete.
     * @throws AntoException Throws AntoException if there is an IOException.
     */
    public void deleteTask(int index) throws AntoException {
        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line != index) {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
    }
}
