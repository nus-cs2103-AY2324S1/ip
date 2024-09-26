import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.SavedDataFormatException;
import exceptions.UpdateDataException;
import tasks.Task;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Sebastian Tay
 */
public class Storage {
    private final String dir = "./VedaMissions";
    private final String storagePath = "./VedaMissions/Missions.txt";
    private final File storageFile = new File(storagePath);

    /**
     * Adds file into the directory specified.
     *
     * @return whether file has been successfully added into the hard disk.
     */
    public boolean addFile() {
        File directory = new File(dir);

        try {
            if (directory.mkdir()) {
                storageFile.createNewFile();

                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to create the file.");
        }

        return false;
    }

    /**
     * Indicates if the saved file is existing.
     *
     * @return true if the saved file does exist, false otherwise.
     */
    public boolean checkFileExists() {
        return storageFile.exists();
    }

    /**
     * Returns the stored task list from the hard disk.
     *
     * @return an ArrayList containing the tasks stored in the hard disk.
     * @throws FileNotFoundException If the storageFile is not located in its rightful directory.
     */
    public ArrayList<Task> retrieveTasks() throws
            FileNotFoundException, SavedDataFormatException, StringIndexOutOfBoundsException {

        final ArrayList<Task> tasks = new ArrayList<>();
        final Scanner sc = new Scanner(storageFile);

        while (sc.hasNextLine()) {
            final String taskData = sc.nextLine(); //Each task is listed in a single line
            tasks.add(Parser.getTaskFromFile(taskData));
        }

        sc.close();

        return tasks;
    }

    /**
     * Updates the saved data in the disk.
     *
     * @param tasks is the current list of tasks the user has.
     * @param isAddingTask indicates whether the user is adding a new task or overwriting the file.
     * @return true if file is successfully updated.
     * @throws IOException When writer is called upon to write after being closed.
     */
    public boolean updateData(ArrayList<Task> tasks, boolean isAddingTask)
            throws IOException, UpdateDataException {
        FileWriter writer = null;
        boolean isNotFirstTask = tasks.size() != 1;
        try {
            writer = new FileWriter(storageFile, isAddingTask);
            String newData = "";

            if (isAddingTask) {
                newData = isNotFirstTask
                        ? (System.lineSeparator() + tasks.get(tasks.size() - 1).convertToStorageForm())
                        : tasks.get(tasks.size() - 1).convertToStorageForm();
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    newData += tasks.get(i).convertToStorageForm();

                    if (i != tasks.size() - 1) {
                        newData += System.lineSeparator();
                    }
                }
            }

            writer.write(newData);
            writer.close();

            return true;

        } catch (IOException e) {
            writer.close();
            throw new UpdateDataException("Failed to update tasks on disk.");
        }
    }
}
