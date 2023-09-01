import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String dir = "/VedaMissions";
    private final String storagePath = "/VedaMissions/Missions.txt";
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

    public boolean checkFileExists() {
        return storageFile.exists();
    }

    /**
     * Returns the stored task list from the hard disk.
     *
     * @throws FileNotFoundException If the storageFile is not located in its rightful directory.
     */
    public ArrayList<Task> retrieveTasks() throws FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(storageFile);

        while (sc.hasNextLine()) {
            final String task = sc.nextLine(); //Each task is listed in a single line

            try {
                char type = task.charAt(0);
                String[] descriptions = task.split("::"); //:: demarcates a different field

                switch (type) {
                    case 'T':
                        tasks.add(new ToDo(descriptions[2], descriptions[1].matches("1")));
                        break;

                    case 'D':
                        //Deadline
                        tasks.add(new Deadline(descriptions[2], descriptions[3], descriptions[1].matches("1")));
                        break;

                    case 'E':
                        //Event
                        tasks.add(new Event(descriptions[2], descriptions[3], descriptions[1].matches("1")));
                        break;

                    default:
                        System.out.println("Wrong file format");
                }

            } catch (StringIndexOutOfBoundsException e) {
                //Formatting error in saved file
                e.printStackTrace();
                System.out.println("File corrupted.");
            }
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
    public boolean updateData(ArrayList<Task> tasks, boolean isAddingTask) throws IOException {
        //TODO overwrite information in storageFile
        FileWriter writer = null;
        boolean isNotFirstTask = tasks.size() != 1;
        try {
            writer = new FileWriter(storageFile, isAddingTask);
            String newData;

            if (isNotFirstTask && isAddingTask) {
                //Add a new line containing the latest task
                newData = (System.lineSeparator() + tasks.get(tasks.size() - 1).convertToStorageForm());

            } else if (!isNotFirstTask && isAddingTask) {
                newData = tasks.get(tasks.size() - 1).convertToStorageForm();
            } else {
                //Overwrite the file
                newData = "";

                //Iterate through tasks and convertToStorageForm
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
            System.out.println("Failed to update tasks on disk.");
            e.printStackTrace();
            return false;
        }
    }
}
